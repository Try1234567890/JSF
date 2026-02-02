package me.tr.trformatter.phases.analysis.scanner.defaults;

import me.tr.trformatter.phases.analysis.scanner.Scanner;
import me.tr.trformatter.phases.analysis.scanner.chars.Characters;
import me.tr.trformatter.phases.analysis.scanner.components.IndexedRawComponent;
import me.tr.trformatter.strings.CString;
import me.tr.trformatter.utility.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericScanner implements Scanner {
    private final Logger LOGGER = LoggerFactory.getLogger(GenericScanner.class);
    private final String openDel;
    private final String closeDel;
    private final Characters characters;

    public GenericScanner(String openDel, String closeDel, Characters characters) {
        Validator.isNull(openDel, "open delimiter is null");
        Validator.isNull(closeDel, "close delimiter is null");
        this.openDel = openDel;
        this.closeDel = closeDel;
        this.characters = characters == null ? new Characters() : characters;
    }

    public GenericScanner(String openDel, String closeDel) {
        this(openDel, closeDel, new Characters());
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
    public List<? extends IndexedRawComponent> scan(String text, int from, int end, int depth) {
        List<IndexedRawComponent> components = new ArrayList<>();
        int start = Math.max(0, from);
        CString cText = new CString(text);

        SearchResult result;
        while (((result = this.findNextComponent(cText, start)).lastIndex() != -1)
                && (end < 0 || result.lastIndex() <= end) && (depth < 0 || components.size() < depth)) {
            start = result.lastIndex();
            components.addAll(result.components());
        }

        return components;
    }

    public List<? extends IndexedRawComponent> scan(String text, int start, int depth) {
        return scan(text, start, -1, depth);
    }

    public List<? extends IndexedRawComponent> scan(String text, int start) {
        return scan(text, start, -1);
    }

    public List<? extends IndexedRawComponent> scan(String text) {
        return scan(text, -1, -1);
    }

    private SearchResult findNextComponent(CString text, int start) {
        List<IndexedRawComponent> rawComponents = new ArrayList<>();
        int odLen = openDel().length();
        int cdLen = closeDel().length();


        int o = text.indexOfIgnoringStrings(openDel(), start);

        if (o == -1) {
            return new SearchResult(rawComponents, -1);
        }

        int c = findCloseIndex(text, o + odLen);

        if (c != -1) {
            String rawComponentStr = text.substring(o + odLen, c);
            rawComponents.add(create(rawComponentStr, o, c + cdLen));

            return new SearchResult(rawComponents, c + cdLen);
        }

        return new SearchResult(rawComponents, o + odLen);
    }

    private int findCloseIndex(CString text, int start) {
        int openAmt = 0;
        // The close counter starts at -1 to offset the fact that 'start' begins
        // inside the placeholder (after the opening delimiter). This ensures
        // nested structures are balanced correctly to find the matching closer.
        int closeAmt = -1;
        char quoteChar = 0; // 0 means outside any string
        char ch = text.charAt(0);

        for (int i = start; i <= text.length(); ch = text.charAt(i), i++) {
            if (quoteChar == 0 && (ch == '\'' || ch == '\"')) {
                quoteChar = ch;
            } else if (quoteChar == ch) {
                quoteChar = 0;
            }


            if (quoteChar == 0 && text.matchesSequence(i, openDel().toCharArray())) {
                openAmt++;
            }

            if (quoteChar == 0 && text.matchesSequence(i, closeDel().toCharArray())) {
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
