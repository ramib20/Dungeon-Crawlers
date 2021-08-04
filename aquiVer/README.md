## Setting up Intellij Configurations

1. Download JavaFX 11.0.2 from https://gluonhq.com/products/javafx/
2. Adding JavaFX to Intellij
    - Go to File -> Project Structure and under Project Settings, click on Libraries
    - Click the leftmost + button and select Java
    - Go to your javafx 11.0.2 folder and select the lib folder
    - Click apply and ok
3. Configuring Project SDK
    - Go to File -> Project Structure and under Project Settings, click on Projects
    - Under Project SDK, select Java version 11.0.6
    - Under Project language level click SDK default (11 ...)
3. Adding JUnits Libraries
    - Go to File -> Project Structure and under Project Settings, click on Libraries
    - Click the leftmost + button and select Maven
    - Add the following four:
        - org.junit.jupiter:junit-jupiter:5.7.0
        - org.testfx:testfx-core:4.0.15-alpha
        - org.testfx:testfx-junit:4.0.15-alpha
        - com.github.almasb:fxgl:11.5
4. Configuring VM options
    - Click the drop-down menu to the left of the play button and select Edit Configurations
    - Under Applications and StartGame, if you do not see a VM option click on modify options and click add vm options
    - Add a VM option with the following command and make sure to change path to your file path to the lib javafx lib folder
    - NOTE: It is very important that you get your file path correct so double check that if you are getting errors
```terminal
--module-path /path/to/javafx/sdk --add-modules javafx.controls,javafx.fxml
```

## Running checkstyle

```python
python3 run_checkstyle.py
```
