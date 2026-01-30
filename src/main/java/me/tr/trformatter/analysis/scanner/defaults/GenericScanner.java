package me.tr.trformatter.analysis.scanner.defaults;

import me.tr.trformatter.Validator;
import me.tr.trformatter.analysis.scanner.Scanner;
import me.tr.trformatter.analysis.scanner.chars.Characters;
import me.tr.trformatter.analysis.scanner.results.IndexedRawComponent;
import me.tr.trformatter.strings.CString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericScanner implements Scanner {
    private final Logger LOGGER = LoggerFactory.getLogger(GenericScanner.class);
    private final String text;
    private final String openDel;
    private final String closeDel;
    private final Characters characters;

    public GenericScanner(String text, String openDel, String closeDel, Characters characters) {
        Validator.isNull(text, "text is null");
        Validator.isNull(openDel, "open delimiter is null");
        Validator.isNull(closeDel, "close delimiter is null");
        this.text = text;
        this.openDel = openDel;
        this.closeDel = closeDel;
        this.characters = characters == null ? new Characters() : characters;
    }

    public GenericScanner(String text, String openDel, String closeDel) {
        this(text, openDel, closeDel, new Characters());
    }

    public String text() {
        return text;
    }

    public String openDel() {
        return openDel;
    }

    public String closeDel() {
        return closeDel;
    }

    public Characters characters() {
        return characters;
    }

    @Override
    public List<? extends IndexedRawComponent> scan(int from, int end, int depth) {
        List<IndexedRawComponent> components = new ArrayList<>();
        LOGGER.debug("\nInitializing scan in {} from {} with depth of {} for components that start with '{}' and end with '{}'", text, from, depth, openDel(), closeDel());
        long startTime = System.nanoTime();
        int start = Math.max(0, from);
        CString cStr = new CString(text());

        SearchResult result;
        while (((result = this.findNextComponent(cStr, start)).lastIndex() != -1)
                && (end < 0 || result.lastIndex() <= end) && (depth < 0 || components.size() < depth)) {
            start = result.lastIndex();
            components.addAll(result.components());
        }


        long endTime = System.nanoTime();
        LOGGER.debug("Scanned {} components from previous scan. ({}ms): {}", components.size(), (endTime - startTime) / 1000000L, components);

        return components;
    }

    public List<? extends IndexedRawComponent> scan(int start, int depth) {
        return scan(start, -1, depth);
    }

    public List<? extends IndexedRawComponent> scan(int start) {
        return scan(start, -1);
    }

    public List<? extends IndexedRawComponent> scan() {
        return scan(-1, -1);
    }

    private SearchResult findNextComponent(CString cStr, int start) {
        List<IndexedRawComponent> rawComponents = new ArrayList<>();
        int odLen = openDel().length();
        int cdLen = closeDel().length();


        int o = cStr.indexOfIgnoringStrings(openDel(), start);

        if (o == -1) {
            return new SearchResult(rawComponents, -1);
        }

        int c = findCloseIndex(o + odLen);

        if (c != -1) {
            String rawComponentStr = text().substring(o + odLen, c);
            rawComponents.add(create(rawComponentStr, o, c + cdLen));

            return new SearchResult(rawComponents, c + cdLen);
        }

        return new SearchResult(rawComponents, o + odLen);
    }

    private int findCloseIndex(int start) {
        CString str = new CString(text());

        int openAmt = 0;
        // The close counter starts at -1 to offset the fact that 'start' begins
        // inside the placeholder (after the opening delimiter). This ensures
        // nested structures are balanced correctly to find the matching closer.
        int closeAmt = -1;
        char quoteChar = 0; // 0 means outside any string
        char ch = str.charAt(0);

        for (int i = start; i <= str.length(); ch = str.charAt(i), i++) {
            if (quoteChar == 0 && (ch == '\'' || ch == '\"')) {
                quoteChar = ch;
            } else if (quoteChar == ch) {
                quoteChar = 0;
            }


            if (quoteChar == 0 && str.matchesSequence(i, openDel().toCharArray())) {
                openAmt++;
            }

            if (quoteChar == 0 && str.matchesSequence(i, closeDel().toCharArray())) {
                closeAmt++;
            }

            if (openAmt == closeAmt) {
                return i;
            }
        }

        return -1;
    }

    protected abstract IndexedRawComponent create(String text, int start, int end);


    public record SearchResult(List<IndexedRawComponent> components, int lastIndex) {
    }
}
