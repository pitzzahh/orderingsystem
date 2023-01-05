package com.mycompany.programmingpt.util;


import com.mycompany.programmingpt.components.LoginFrame;
import com.mycompany.programmingpt.components.OrderFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.InputStream;
import java.util.Properties;

public class ResourceUtils {
    private static Properties config = null;

    public static Properties getConfig() {
        if (config == null) {
            config = new Properties();
            try (InputStream input = OrderFrame.class.getClassLoader().getResourceAsStream("app.properties")) {
                config.load(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return config;
    }

    public static InputStream getImageResource(String name) {
        return ResourceUtils.class.getResourceAsStream("/image/" + name);
    }
}
