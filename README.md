# CRUD_operations
___
Ранний учебный прототип системы Умный Дом, базирующйся на csv, xml и H2 источниках данных.

<b>В CLI не были добавлены: команды для взаимодействия с DataProviderHibernate (В дефолтной конфигурации работает с PostgreS), реализация всех видов сохранения коллекций и наследованных объектов в БД.</b>

java -jar <название_файла> -env <путь_до_environment_properties> -log <путь_до_log4j2_xml> -h - вызов вспомогательной информации о программе.

Если не указывать -env и -log то будут использованы настройки по умолчанию. В лог будет выводиться только info и error информация о происходящих процессках.

Некоторые методы, относящиеся к взаимодействию не с бинами, а с устройствами напрямую, представлены заглушками.

Найденные баги:
Метод update ломает xml файл, если на момент вызова в файле присутствует только одна запись

Для Xml нет генератора id, поэтому через append можно добавить в файл одинаковые записи

Options
-add <deviceStatus deviceType device_id familyFriendly|powerSavingMode|lastMeasurement|savedFootage volume|onPower|measuresFrequency|lastAlarm brightness|timeOfDay|sensorType|onGuard> - Creating new device with type SENSOR, MULTIMEDIA, SECURITY, LIGHTING\n 

-adu <user_id user_name accessLevel> - Creating new user

-dld <device_id deviceType> - Deleting device by selected id

-dlu <user_id> - Deleting user by selected id

-dt <data_type> - Select type of data - CSV, XML, H2 (default: h2)

-env <filePath> - Path to the environment.properties file

-gtd <device_id deviceType> - Get device by selected id

-gtu <user_id> - Get user by selected id

-h - Show information about console commands

-log <arg> - Path to the log4j2 configuration file

-pa <command device_id deviceType delay message> - Forces selected device to execute given instruction. Can be extended with delay in seconds and message for notification in case of delay

-sd <device_id deviceType> - Shares data between system and device. If it's a sensor, gets measurements from it. If it's not a sensor, gives it instructions

-upd <device_id deviceStatus deviceType familyFriendly|powerSavingMode|lastMeasurement|savedFootage volume|onPower|measuresFrequency|lastAlarm brightness|timeOfDay|sensorType|onGuard> - Updating selected device or creating new

-upu <user_id user_name accessLevel> - Updating selected user or creating new
