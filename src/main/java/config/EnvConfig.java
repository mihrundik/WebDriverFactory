package config;

public class EnvConfig {

    // читаем переменные из файла типа setenv.sh
    public static String getUrl() {
        return System.getenv("TEST_URL");
    }

    public static String getUsername() {
        return System.getenv("TEST_USERNAME");
    }

    public static String getEmail() {
        return System.getenv("TEST_EMAIL");
    }

    public static String getPassword() {
        return System.getenv("TEST_PASSWORD");
    }

    public static String getCPassword() {
        return System.getenv("TEST_CPASSWORD");
    }

    public static String getBirthDay() {
        return System.getenv("TEST_BIRTHDATE_DD");
    }

    public static String getBirthMonth() {
        return System.getenv("TEST_BIRTHDATE_MM");
    }

    public static String getBirthYear() {
        return System.getenv("TEST_BIRTHDATE_YYYY");
    }

    public static String getLevel() {
        return System.getenv("TEST_LEVEL");
    }

    public static String getLevelIngl() {
        return System.getenv("TEST_LEVEL_INGL");
    }

}
