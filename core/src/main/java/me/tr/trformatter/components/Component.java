package me.tr.trformatter.components;

import me.tr.trformatter.uids.UID;

public interface Component {

    /**
     * Retrieve the component identifier.
     *
     * @return The component {@link UID}
     */
    UID getUID();

}
