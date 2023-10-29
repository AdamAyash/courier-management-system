package bg.tu_varna.sit.couriermanagementsystem.configmanager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*Клас за достъпване на конфигурационни файлове*/
public  class ConfigManager
{
    //-------------------------
    //Constants:
    //-------------------------
    private final String _CONFIG_FILE_NAME = "Properties.properties";

    //-------------------------
    //Members:
    //-------------------------
    private Properties _properties;
    private FileInputStream  _fileInputStream;
    private String _configFilePath;

    public Properties getProperties()
    {
        return  _properties;
    }

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public ConfigManager() throws IOException
    {
        _properties = new Properties();
        _configFilePath = getConfigFile();
        _fileInputStream = new FileInputStream(_configFilePath);
        _properties.load(_fileInputStream);
    }
    //-------------------------
    //Methods:
    //-------------------------
    private String getConfigFile()
    {
        return System.getProperty("user.dir") + "\\data\\" + _CONFIG_FILE_NAME;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
