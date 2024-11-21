package by.it_academy.jd2.commonlib.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Actions {

    public static final String AUDIT_USER_CREATE = "Добавлен новый пользователь";
    public static final String AUDIT_USER_UPDATE = "Обновлены данные пользователя";
    public static final String AUDIT_USER_LOGIN = "Пользователь вошел в систему";
    public static final String AUDIT_USER_VERIFY = "Пользователь прошел верификацию";

    public final static String CREATE_CURRENCY = "Создана валюта";
    public final static String CREATE_CATEGORY = "Создана категория операции";


}
