package trformatter;

import me.tr.trformatter.phases.analysis.scanner.chars.CharacterSet;
import me.tr.trformatter.strings.CString;
import me.tr.trformatter.strings.cases.*;
import me.tr.trformatter.strings.color.ansi.ANSI4Bit;
import me.tr.trformatter.strings.color.converter.ColorConverter;
import org.junit.jupiter.api.Test;

public class FormatterTest {


    @Test
    public void testCase() {
        CString word = new CString("camelCase");
        System.out.println(word.toCase(CaseType.PASCAL));
    }

    @Test
    public void testSinglePlaceholder() {
        String pl = "${#[current_dir()]#}$\\images";

        System.out.println(TrFormatterAPI.format(pl));
    }

    @Test
    public void testIsDefaultChars() {
        System.out.println("Yes: " + CharacterSet.DEFAULT.isDefault());
        System.out.println("No: " + new CharacterSet(
                "{{",        // openPlaceholder
                "}}",        // closePlaceholder
                "<%",        // openTag
                "%>",        // closeTag
                "{?",        // openCondition
                "?}",        // closeCondition
                "||",        // orCondition
                "&&",        // andCondition
                ";",         // separateConditions
                "#[",        // openFunction
                "]",         // closeFunction
                "|",         // separateFunction
                "(",         // openParams
                ")",         // closeParams
                ",",         // splitParams
                ":"          // associateParams
        ).isDefault());
    }

    @Test
    public void testCustomChars() {
        String text = "ssawedbnuhioj {{<%user_home()%>}}";


        System.out.println(
                TrFormatterAPI.format(text,
                        CharacterSet.builder()
                                .setOpenPlaceholder("{{")
                                .setClosePlaceholder("}}")
                                .setOpenTag("<%")
                                .setCloseTag("%>")
                                .build()
                ));
    }
}
