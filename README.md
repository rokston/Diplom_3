# Diplom_3

Перед запуском тестов необходимо изменить 
путь к вебдрайверу и exe файлу браузера на свой.

По дефолту запускаются тесты в браузере Chrome.
Для запуска в браузере яндекс можно установить 
System Property "browser" = yandex.
-----

Use git bash if running on Windows

mvn -Dwebdriver.chrome.driver=/opt/chromedriver/chromedriver \
-Dwebdriver.chrome.binary=/opt/chrome-for-testing/chrome \
-Dwebdriver.yandex.driver=/opt/chromedriver/chromedriver-116 \
-Dwebdriver.yandex.binary=/usr/bin/yandex-browser \
-Dbrowser=yandex test