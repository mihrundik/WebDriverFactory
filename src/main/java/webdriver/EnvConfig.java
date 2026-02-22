package webdriver;

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

//
//    public static final String TEST_URL = "https://otus.home.kartushin.su/form.html";
//    public static final String TEST_USERNAME = "test_user";
//    public static final String TEST_EMAIL = "test@test.ru";
//    public static final String TEST_PASSWORD = "123";
//    public static final String TEST_CPASSWORD = "123"; // подтверждение пароля
//    public static final String TEST_BIRTHDATE_DD = "21";
//    public static final String TEST_BIRTHDATE_MM = "02";
//    public static final String TEST_BIRTHDATE_YYYY = "2020";
//    public static final String TEST_LEVEL = "Продвинутый";
//    public static final String TEST_LEVEL_INGL = "advanced";
//
//    public static String getUrl() {
//        return TEST_URL;
//    }
//
//    public static String getUsername() {
//        return TEST_USERNAME;
//    }
//
//    public static String getEmail() {
//        return TEST_EMAIL;
//    }
//
//    public static String getPassword() {
//        return TEST_PASSWORD;
//    }
//
//    public static String getCPassword() {
//        return TEST_CPASSWORD;
//    }
//
//    public static String getBirthDay() {
//        return TEST_BIRTHDATE_DD;
//    }
//
//    public static String getBirthMonth() {
//        return TEST_BIRTHDATE_MM;
//    }
//
//    public static String getBirthYear() {
//        return TEST_BIRTHDATE_YYYY;
//    }
//
//    public static String getLevel() {
//        return TEST_LEVEL;
//    }
//
//    public static String getLevelIngl() {
//        return TEST_LEVEL_INGL;
//    }
}
