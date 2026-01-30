package me.tr.trformatter.defaults.tags.sendmessage;

import me.tr.trformatter.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.components.Tag;
import me.tr.trformatter.uids.UID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;

public class SendMessage extends Tag {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMessage.class);

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
            LOGGER.error("An error occurs while writing {} to output stream", msg, e);
        }
        return "";
    }

    private byte[] getByteArray(String msg) {
        if (msg == null) return new byte[0];
        return msg.getBytes();
    }

    private String getMessage(ParamsContainer params) {
        String msg = params.getAs("message", String.class);
        if (msg == null) {
            LOGGER.warn("The message is null for send message, using an empty string.");
            return "";
        }
        return msg;
    }

    private OutputStream getOut(ParamsContainer params) {
        String out = params.getAs("out", String.class);
        return SendMessageOutput.parse(out).getOut(out);
    }

    private String getPrefix(ParamsContainer params) {
        String prefix = params.getAs("prefix", String.class);
        return prefix == null ? "" : prefix;
    }
}
