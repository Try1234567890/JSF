package me.tr.trformatter.defaults.tags.sendmessage;

import me.tr.trformatter.components.Tag;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.uids.UID;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

public class SendMessage extends Tag {

    public SendMessage() {
        super(new UID("send_message"));
    }

    /**
     * Evaluate this component.
     *
     * @param params The params found for this component.
     * @return the string to replace with placeholder in raw text.
     */
    @Override
    public String evaluate(ParamsContainer params) {
        String msg = getPrefix(params) + getMessage(params);
        try (OutputStream out = getOut(params)) {
            out.write(getByteArray(msg));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return "";
    }

    private byte[] getByteArray(String msg) {
        if (msg == null) return new byte[0];
        return msg.getBytes();
    }

    private String getMessage(ParamsContainer params) {
        Optional<String> msg = params.getAs("message", String.class);
        if (msg.isEmpty()) {
            new NullPointerException("The message is null for send message, using an empty string.").printStackTrace(System.err);
            return "";
        }
        return msg.get();
    }

    private OutputStream getOut(ParamsContainer params) {
        String out = params.getAsOrNull("out", String.class);
        return SendMessageOutput.parse(out).getOut(out);
    }

    private String getPrefix(ParamsContainer params) {
        return params.getAs("prefix", String.class).orElse("");
    }
}
