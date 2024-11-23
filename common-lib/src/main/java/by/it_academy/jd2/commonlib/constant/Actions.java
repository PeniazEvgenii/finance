package by.it_academy.jd2.commonlib.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Actions {

    public static final String AUDIT_USER_CREATE = "Добавлен новый пользователь";
    public static final String AUDIT_USER_UPDATE = "Обновлены данные пользователя";
    public static final String AUDIT_USER_LOGIN = "Пользователь вошел в систему";
    public static final String AUDIT_USER_VERIFY = "Пользователь прошел верификацию";

    public static final String AUDIT_CURRENCY_CREATE = "Создана валюта";
    public static final String AUDIT_CATEGORY_CREATE = "Создана категория операции";

    public static final String AUDIT_ACCOUNT_CREATE = "Добавлен новый счет";
    public static final String AUDIT_ACCOUNT_UPDATE = "Счет обновлен";
    public static final String AUDIT_OPERATION_CREATE = "Создана новая операция";
    public static final String AUDIT_OPERATION_UPDATE = "Обновлена операция";
    public static final String AUDIT_OPERATION_DELETE = "Удалена операция";


}
