package com.narkomodz;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NarkoModz implements ModInitializer {
    public static final String MOD_ID = "narkomodz";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("NarkoModz aktif!");
    }
}
