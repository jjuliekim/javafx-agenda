package cs3500.pa05.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.EventJson;
import cs3500.pa05.model.JsonUtils;
import cs3500.pa05.model.TaskJson;
import cs3500.pa05.model.WeekJson;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextFlow;

/**
 * Represents the cs3500.pa05.controller for the bullet journal application
 */
public class BulletJournalController {
  @FXML
  private VBox mainvBox = new VBox();
  @FXML
  private Button addEventButton = new Button();
  @FXML
  private Button addTaskButton = new Button();
  @FXML
  private Button saveToFileButton;
  @FXML
  private Button openFileButton = new Button();
  @FXML
  private Button newWeekButton = new Button();
  @FXML
  private Button openTemplateButton;
  private ArrayList<Day> days;
  @FXML
  private VBox taskQueue;
  @FXML
  private MenuBar menuBar = new MenuBar();
  @FXML
  private Label numOfEvents = new Label();
  private int eventCounter = 0;
  @FXML
  private Button fontButton = new Button();
  @FXML
  private Label numOfTasks = new Label();
  private int taskCounter = 0;
  @FXML
  private Button maxEventsButton = new Button();
  private int maxEvents = -1;
  @FXML
  private Button maxTasksButton = new Button();
  private int maxTasks = -1;
  @FXML
  private Button customThemeButton = new Button();
  @FXML
  private TextArea quotesNotes;
  @FXML
  private VBox sundayVbox;
  @FXML
  private VBox mondayVbox;
  @FXML
  private VBox tuesdayVbox;
  @FXML
  private VBox wednesdayVbox;
  @FXML
  private VBox thursdayVbox;
  @FXML
  private VBox fridayVbox;
  @FXML
  private VBox saturdayVbox;
  @FXML
  private TextField title;
  @FXML
  private Label tasksLabel = new Label();
  @FXML
  private Label sunLabel = new Label();
  @FXML
  private Label monLabel = new Label();
  @FXML
  private Label tuesLabel = new Label();
  @FXML
  private Label wedsLabel = new Label();
  @FXML
  private Label thursLabel = new Label();
  @FXML
  private Label friLabel = new Label();
  @FXML
  private Label satLabel = new Label();
  @FXML
  private Label quotesLabel = new Label();
  @FXML
  private ProgressBar sunProgress;
  @FXML
  private Label sunProgressLabel;
  @FXML
  private ProgressBar monProgress;
  @FXML
  private Label monProgressLabel;
  @FXML
  private ProgressBar tuesProgress;
  @FXML
  private Label tuesProgressLabel;
  @FXML
  private ProgressBar wedProgress;
  @FXML
  private Label wedProgressLabel;
  @FXML
  private ProgressBar thursProgress;
  @FXML
  private Label thursProgressLabel;
  @FXML
  private ProgressBar friProgress;
  @FXML
  private Label friProgressLabel;
  @FXML
  private ProgressBar satProgress;
  @FXML
  private Label satProgressLabel;
  @FXML
  private Label maxNumOfEvents;
  @FXML
  private Label maxNumOfTasks;
  @FXML
  private Label percentTasks = new Label();
  @FXML
  private Button themeButton = new Button();
  private ArrayList<VBox> vboxes;
  private ArrayList<ColorPicker> colorPickers;
  @FXML
  private ScrollPane taskScrollPane = new ScrollPane();

  /**
   * Constructor for BulletJournalController
   *
   * @param days the week
   */
  public BulletJournalController(ArrayList<Day> days) {
    this.days = days;
  }

  /**
   * Initializes the bullet journal application
   *
   * @param scene the scene
   */
  @FXML
  public void run(Scene scene) {
    addEventButton.setOnAction(e -> handleAddEvent());
    addTaskButton.setOnAction(e -> handleAddTask());
    saveToFileButton.setOnAction(e -> handleSaveFile());
    maxEventsButton.setOnAction(e -> handleMaxEvents());
    maxTasksButton.setOnAction(e -> handleMaxTasks());
    themeButton.setOnAction(e -> handleTheme());
    newWeekButton.setOnAction(e -> handleNewWeek());
    openFileButton.setOnAction(e -> handleOpenFile());
    fontButton.setOnAction(e -> handleFontStyle());
    customThemeButton.setOnAction(e -> handleCustomTheme());
    openTemplateButton.setOnAction(e -> handleOpenTemplate());
    setShortcuts(scene);
    for (Menu menu : menuBar.getMenus()) {
      menu.setStyle("-fx-focus-color: transparent; -fx-accent: transparent;");
    }
    setup();
  }

  /**
   * Sets the keyboard shortcuts for the bujo
   *
   * @param scene the scene
   */
  private void setShortcuts(Scene scene) {
    scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
      if (new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN).match(event)) {
        handleTheme();
      }
    });

    scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
      if (new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_DOWN).match(event)) {
        handleAddEvent();
      }
    });

    scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
      if (new KeyCodeCombination(KeyCode.T, KeyCombination.SHORTCUT_DOWN).match(event)) {
        handleAddTask();
      }
    });

    scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
      if (new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN).match(event)) {
        handleSaveFile();
      }
    });

    scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
      if (new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN).match(event)) {
        handleOpenFile();
      }
    });

    scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
      if (new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN).match(event)) {
        handleNewWeek();
      }
    });

    scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
      if (new KeyCodeCombination(KeyCode.F, KeyCombination.SHORTCUT_DOWN).match(event)) {
        handleFontStyle();
      }
    });
  }

  /**
   * sets up the bullet journal application
   */
  public void setup() {
    this.title.setText("Bullet Journal");
    colorPickers = new ArrayList<>();
    for (int i = 0; i < 12; i++) {
      colorPickers.add(new ColorPicker(Color.WHITE));
    }

    if (maxTasks == -1) {
      maxNumOfTasks.setText(String.valueOf(""));
    } else {
      maxNumOfTasks.setText(String.valueOf(maxTasks));
    }

    if (maxEvents == -1) {
      maxNumOfEvents.setText("");
    } else {
      maxNumOfEvents.setText(String.valueOf(maxEvents));
    }

    sundayVbox.getChildren().clear();
    mondayVbox.getChildren().clear();
    tuesdayVbox.getChildren().clear();
    wednesdayVbox.getChildren().clear();
    thursdayVbox.getChildren().clear();
    fridayVbox.getChildren().clear();
    saturdayVbox.getChildren().clear();

    this.taskQueue.getChildren().clear();
    this.getCompletedTasks();
    this.weeksProgressBars();

    this.vboxes = new ArrayList<>();
    this.vboxes.add(sundayVbox);
    this.vboxes.add(mondayVbox);
    this.vboxes.add(tuesdayVbox);
    this.vboxes.add(wednesdayVbox);
    this.vboxes.add(thursdayVbox);
    this.vboxes.add(fridayVbox);
    this.vboxes.add(saturdayVbox);

    eventCounter = 0;
    taskCounter = 0;

    for (int i = 0; i < 7; i += 1) {
      Day d = days.get(i);
      for (EventJson e : d.getEvents()) {
        this.vboxes.get(i).getChildren().add(e.toTextFlow());
        eventCounter += 1;
      }

      for (TaskJson t : d.getTasks()) {
        TextFlow textflow = t.toTextFlow();
        this.vboxes.get(i).getChildren().add((textflow));
        if (t.isCompleted()) {
          CheckBox cb = new CheckBox(t.getName() + " [COMPLETED]");
          cb.setFont(new Font(14));
          this.taskQueue.getChildren().add(cb);
          cb.setOnAction(e -> handleCompleteTask(t, cb, textflow));
          cb.setSelected(true);
        } else {
          CheckBox cb = new CheckBox(t.getName() + " [NOT COMPLETED]");
          cb.setFont(new Font(14));
          this.taskQueue.getChildren().add(new CheckBox(t.getName() + " [NOT COMPLETED]"));
          cb.setOnAction(e -> handleCompleteTask(t, cb, textflow));
        }

        taskCounter += 1;
      }

      numOfTasks.setText(String.valueOf(taskCounter));
      numOfEvents.setText(String.valueOf(eventCounter));
    }
  }

  /**
   * Displays a dialog to add an event
   */
  private void handleAddEvent() {
    Dialog<EventJson> dialog = new Dialog<>();
    dialog.setTitle("New Event");
    Label nameLabel = new Label("Event Name");
    Label descLabel = new Label("Description");
    Label dayLabel = new Label("Event Day");
    Label timeLabel = new Label("Time");
    Label durLabel = new Label("Duration");
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(5);
    gridPane.add(nameLabel, 0, 0);
    gridPane.add(descLabel, 0, 1);
    gridPane.add(dayLabel, 0, 2);
    gridPane.add(timeLabel, 0, 3);
    gridPane.add(durLabel, 0, 4);
    TextArea desc = new TextArea();
    desc.setWrapText(true);
    desc.setMaxWidth(240);
    desc.setMaxHeight(150);
    ChoiceBox<DayOfWeek> day = new ChoiceBox<>();
    for (DayOfWeek d : DayOfWeek.values()) {
      day.getItems().add(d);
    }

    TextField hour = new TextField();
    hour.setMaxWidth(30);
    Label colon = new Label(":");
    colon.setMaxWidth(5);
    TextField minute = new TextField();
    minute.setMaxWidth(30);
    ChoiceBox<String> ampm = new ChoiceBox<>();
    ampm.setMaxWidth(50);
    ampm.getItems().addAll("AM", "PM");
    HBox timeBox = new HBox();
    timeBox.getChildren().addAll(hour, colon, minute, ampm);
    timeBox.setAlignment(Pos.CENTER_LEFT);
    timeBox.setSpacing(10);

    TextField dur = new TextField();
    dur.setMaxWidth(30);
    ChoiceBox<String> durUnit = new ChoiceBox<>();
    durUnit.getItems().addAll("minutes", "hours", "days", "weeks", "months", "years");
    durUnit.setMaxWidth(70);
    HBox durBox = new HBox(dur, durUnit);
    durBox.setAlignment(Pos.CENTER_LEFT);
    durBox.setSpacing(10);

    TextField name = new TextField();
    gridPane.add(name, 1, 0);
    gridPane.add(desc, 1, 1);
    gridPane.add(day, 1, 2);
    gridPane.add(timeBox, 1, 3);
    gridPane.add(durBox, 1, 4);
    dialog.getDialogPane().setContent(gridPane);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.setResultConverter(param -> {
      if (param == ButtonType.OK) {
        StringBuilder time = new StringBuilder();
        time.append(hour.getText()).append(minute.getText());
        StringBuilder duration = new StringBuilder();
        duration.append(dur.getText()).append(" ").append(durUnit.getValue());

        try {
          Integer.parseInt(time.toString());
        } catch (NumberFormatException e) {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Invalid Number Input");
          alert.setContentText("Please enter valid time.");
          alert.showAndWait();
          return null;
        }

        try {
          Integer.parseInt(dur.getText());
        } catch (NumberFormatException e) {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText("Invalid Number Input");
          alert.setContentText("Please enter valid duration.");
          alert.showAndWait();
          return null;
        }

        StringBuilder timeText = new StringBuilder();
        timeText.append(hour.getText()).append(":").append(minute.getText())
            .append(" ").append(ampm.getValue());

        EventJson e = new EventJson(name.getText(), desc.getText(), day.getValue(),
            timeText.toString(), duration.toString());
        addEventToVbox(day.getValue().ordinal(), e);
        return e;
      } else {
        return null;
      }
    });
    dialog.showAndWait();
  }

  /**
   * Adds an event to the appropriate vbox and checks max restrictions
   *
   * @param day day of the week to add the event to
   * @param e   event to add
   */
  public void addEventToVbox(int day, EventJson e) {
    if (days.get(day).getEvents().size() >= maxEvents && maxEvents != -1) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Max Events Reached");
      alert.setContentText("You have reached the maximum number of events for this week.");
      alert.showAndWait();
    } else {
      this.days.get(day).addEvent(e);
      TextFlow textflow = e.toTextFlow();
      this.vboxes.get(day).getChildren().add(textflow);
      eventCounter++;
      numOfEvents.setText(String.valueOf(eventCounter));
    }
  }

  /**
   * Displays a dialog to add a task
   */
  private void handleAddTask() {
    Dialog<TaskJson> dialog = new Dialog<>();
    dialog.setTitle("New Task");
    Label nameLabel = new Label("Task Name");
    Label descLabel = new Label("Description");
    Label dayLabel = new Label("Task Day");
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(5);
    gridPane.add(nameLabel, 0, 0);
    gridPane.add(descLabel, 0, 1);
    gridPane.add(dayLabel, 0, 2);
    TextArea desc = new TextArea();
    desc.setWrapText(true);
    desc.setMaxWidth(240);
    desc.setMaxHeight(150);
    ChoiceBox<DayOfWeek> day = new ChoiceBox<>();
    for (DayOfWeek d : DayOfWeek.values()) {
      day.getItems().add(d);
    }
    TextField name = new TextField();
    gridPane.add(name, 1, 0);
    gridPane.add(desc, 1, 1);
    gridPane.add(day, 1, 2);
    dialog.getDialogPane().setContent(gridPane);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.setResultConverter(param -> {
      if (param == ButtonType.OK) {
        ArrayList<Boolean> completion = new ArrayList<>();
        completion.add(false);

        TaskJson t = new TaskJson(name.getText(), desc.getText(), day.getValue(), completion);
        addTaskToVbox(day.getValue().ordinal(), t);
        return t;
      } else {
        return null;
      }
    });
    dialog.showAndWait();
  }

  /**
   * Adds a task to the appropriate vbox if the number of tasks has not reached the max
   *
   * @param day day of the week to add the task to
   * @param t   task to add
   */
  public void addTaskToVbox(int day, TaskJson t) {
    if (days.get(day).getTasks().size() >= maxTasks && maxTasks != -1) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Max Tasks Reached");
      alert.setContentText("You have reached the maximum number of tasks for this week.");
      alert.showAndWait();
    } else {
      this.days.get(day).addTask(t);
      TextFlow textflow = t.toTextFlow();
      this.vboxes.get(day).getChildren().add(textflow);
      CheckBox cb = new CheckBox(t.getName() + " [NOT COMPLETED]");
      cb.setFont(new Font(14));
      this.taskQueue.getChildren().add(cb);
      cb.setOnAction(e -> handleCompleteTask(t, cb, textflow));
      taskCounter++;
      numOfTasks.setText(String.valueOf(taskCounter));
      weeksProgressBars();
    }
  }

  private void handleCompleteTask(TaskJson t, CheckBox cb, TextFlow taskTextFlow) {
    if (cb.isSelected()) {
      t.setCompleted(true);
      cb.setText(t.getName() + " [COMPLETED]");
    } else {
      t.setCompleted(false);
      cb.setText(t.getName() + " [NOT COMPLETED]");
    }

    taskTextFlow.getChildren().clear();
    List<Node> taskNodes = new ArrayList<>(t.toTextFlow().getChildren());
    for (Node taskNode : taskNodes) {
      taskTextFlow.getChildren().add(taskNode);
    }
    getCompletedTasks();
    weeksProgressBars();
  }

  /**
   * calls the updateProgressBar method for each day of the week
   */
  private void weeksProgressBars() {
    updateProgressBar(sunProgressLabel, sunProgress, 0);
    updateProgressBar(monProgressLabel, monProgress, 1);
    updateProgressBar(tuesProgressLabel, tuesProgress, 2);
    updateProgressBar(wedProgressLabel, wedProgress, 3);
    updateProgressBar(thursProgressLabel, thursProgress, 4);
    updateProgressBar(friProgressLabel, friProgress, 5);
    updateProgressBar(satProgressLabel, satProgress, 6);
  }

  /**
   * gets the percentage of completed tasks and updates the label
   */
  private void getCompletedTasks() {
    double completed = 0;
    for (Node task : taskQueue.getChildren()) {
      if (((CheckBox) task).isSelected()) {
        completed++;
      }
    }
    percentTasks.setText((double) Math.round((completed / taskQueue.getChildren().size()) * 10000)
        / 100 + "%");
  }

  /**
   * updates the progress bar of the given day
   *
   * @param label label to update
   * @param bar   progress bar to update
   * @param day   day of the week to update
   */
  private void updateProgressBar(Label label, ProgressBar bar, int day) {
    int completed = 0;
    for (TaskJson task : days.get(day).getTasks()) {
      if (task.isCompleted()) {
        completed++;
      }
    }
    if (days.get(day).getTasks().size() != 0) {
      label.setText(completed + "/" + days.get(day).getTasks().size());
      bar.setProgress(((double) completed) / days.get(day).getTasks().size());
    }
  }

  /**
   * Handles saving a bullet journal to a .bujo file
   */
  public void handleSaveFile() {
    Dialog<WeekJson> dialog = new Dialog<>();
    dialog.setTitle("Save File");

    Label saveLabel = new Label("Save file as: ");
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(5);
    gridPane.add(saveLabel, 0, 0);
    TextField file = new TextField();
    gridPane.add(file, 1, 0);

    dialog.getDialogPane().setContent(gridPane);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.setResultConverter(param -> {
      if (param == ButtonType.OK) {
        WeekJson week =
            new WeekJson(days, maxEvents, maxTasks, quotesNotes.getText(), title.getText(),
                menuBar.getStyle(), mainvBox.getStyle());
        writeToFile(file.getText(), week);
        return week;
      } else {
        return null;
      }
    });
    dialog.showAndWait();
  }

  /**
   * Writes a week to a .bujo file
   *
   * @param filename name of .bujo file
   * @param week     bujo week to write
   */
  public void writeToFile(String filename, WeekJson week) {
    try {
      JsonNode serializedMessage = JsonUtils.serializeRecord(week);
      try (PrintStream output = new PrintStream(filename + ".bujo")) {
        output.println(serializedMessage);
      }
    } catch (IOException exc) {
      System.err.println("Error while trying to save to a .bujo file");
    }
  }

  /**
   * Asks for max number of events each day
   */
  private void handleMaxEvents() {
    HBox horizontalBox = new HBox();
    Label label = new Label("Max number of events for each day:");
    TextField textField = new TextField();
    textField.setMaxWidth(50);
    horizontalBox.getChildren().addAll(label, textField);
    horizontalBox.setSpacing(10);
    horizontalBox.setAlignment(Pos.CENTER);
    Dialog<Integer> dialog = new Dialog<>();
    dialog.setTitle("Max Event Number");
    dialog.getDialogPane().setContent(horizontalBox);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.setResultConverter(param -> {
      if (param == ButtonType.OK) {
        maxEvents = Integer.parseInt(textField.getText());
        maxNumOfEvents.setText(String.valueOf(maxEvents));
        return maxEvents;
      } else {
        return null;
      }
    });
    dialog.showAndWait();
  }

  /**
   * asks for max number of tasks each day
   */
  private void handleMaxTasks() {
    HBox horizontalBox = new HBox();
    Label label = new Label("Max number of tasks for each day:");
    TextField textField = new TextField();
    textField.setMaxWidth(50);
    horizontalBox.getChildren().addAll(label, textField);
    horizontalBox.setSpacing(10);
    horizontalBox.setAlignment(Pos.CENTER);
    Dialog<Integer> dialog = new Dialog<>();
    dialog.setTitle("Max Task Number");
    dialog.getDialogPane().setContent(horizontalBox);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.setResultConverter(param -> {
      if (param == ButtonType.OK) {
        maxTasks = Integer.parseInt(textField.getText());
        maxNumOfTasks.setText(String.valueOf(maxTasks));
        return maxTasks;
      } else {
        return null;
      }
    });
    dialog.showAndWait();
  }

  /**
   * changes the color theme of the application
   */
  private void handleTheme() {
    taskScrollPane.setStyle("-fx-background: white");
    Random random = new Random();
    int rand = random.nextInt(9);
    ArrayList<ProgressBar> progressBars =
        new ArrayList<>(Arrays.asList(monProgress, tuesProgress, wedProgress, thursProgress,
            friProgress, satProgress, sunProgress));
    switch (rand) {
      case 0 -> {
        mainvBox.setStyle("-fx-background-color: #ebfaf4");
        menuBar.setStyle("-fx-background-color: #468e7f");
        for (ProgressBar bar : progressBars) {
          bar.setStyle("-fx-accent: #468e7f");
        }
        title.setEffect(new DropShadow(10, Color.web("#24c9a6")));
        menuBar.setEffect(new DropShadow(8, Color.web("90EAD7FF")));
      }
      case 1 -> {
        mainvBox.setStyle("-fx-background-color: #e6f3fd");
        menuBar.setStyle("-fx-background-color: #5d819d");
        for (ProgressBar bar : progressBars) {
          bar.setStyle("-fx-accent: #5d819d");
        }
        title.setEffect(new DropShadow(10, Color.web("#5d819d")));
        menuBar.setEffect(new DropShadow(8, Color.web("6EB3E8FF")));
      }
      case 2 -> {
        mainvBox.setStyle("-fx-background-color: #ece6fd");
        menuBar.setStyle("-fx-background-color: #584a7f");
        for (ProgressBar bar : progressBars) {
          bar.setStyle("-fx-accent: #584a7f");
        }
        title.setEffect(new DropShadow(10, Color.web("#584a7f")));
        menuBar.setEffect(new DropShadow(8, Color.web("896ED7FF")));
      }
      case 3 -> {
        mainvBox.setStyle("-fx-background-color: #f8f5ec");
        menuBar.setStyle("-fx-background-color: #f4d782");
        for (ProgressBar bar : progressBars) {
          bar.setStyle("-fx-accent: #f4d782");
        }
        title.setEffect(new DropShadow(10, Color.web("#ffc936")));
        menuBar.setEffect(new DropShadow(8, Color.web("F4D782FF")));
      }
      case 4 -> {
        mainvBox.setStyle("-fx-background-color: #f5faea");
        menuBar.setStyle("-fx-background-color: #a9e66f");
        for (ProgressBar bar : progressBars) {
          bar.setStyle("-fx-accent: #a9e66f");
        }
        title.setEffect(new DropShadow(10, Color.web("#7bab4e")));
        menuBar.setEffect(new DropShadow(8, Color.web("A9E66FFF")));
      }
      case 5 -> {
        mainvBox.setStyle("-fx-background-color: #faeded");
        menuBar.setStyle("-fx-background-color: #ea7171");
        for (ProgressBar bar : progressBars) {
          bar.setStyle("-fx-accent: #ea7171");
        }
        title.setEffect(new DropShadow(10, Color.web("#ea7171")));
        menuBar.setEffect(new DropShadow(8, Color.web("FFA8A8FF")));
      }
      case 6 -> {
        mainvBox.setStyle("-fx-background-color: #fadddd");
        menuBar.setStyle("-fx-background-color: #dc7474");
        for (ProgressBar bar : progressBars) {
          bar.setStyle("-fx-accent: #dc7474");
        }
        title.setEffect(new DropShadow(10, Color.web("#890808")));
        menuBar.setEffect(new DropShadow(8, Color.web("DC7474FF")));
      }
      case 7 -> {
        mainvBox.setStyle("-fx-background-color: #f4f4f4");
        menuBar.setStyle("-fx-background-color: #dadada");
        for (ProgressBar bar : progressBars) {
          bar.setStyle("-fx-accent: #27618e");
        }
        title.setEffect(new DropShadow(10, Color.web("#6b6b6b")));
        menuBar.setEffect(new DropShadow(8, Color.web("909090FF")));
      }
      case 8 -> {
        mainvBox.setStyle("-fx-background-color: #9b9191");
        menuBar.setStyle("-fx-background-color: #3c3939");
        for (ProgressBar bar : progressBars) {
          bar.setStyle("-fx-accent: #484545");
        }
        title.setEffect(new DropShadow(10, Color.web("#2a2a2a")));
        menuBar.setEffect(new DropShadow(8, Color.web("484545FF")));
      }
      default -> {
        mainvBox.setStyle("-fx-background-color: #fdeee6");
        menuBar.setStyle("-fx-background-color: #d76218");
        for (ProgressBar bar : progressBars) {
          bar.setStyle("-fx-accent: #d76218");
        }
        title.setEffect(new DropShadow(10, Color.web("#ff6200")));
        menuBar.setEffect(new DropShadow(8, Color.web("D09773FF")));
      }
    }
  }


  private void handleOpenFile() {
    Dialog<WeekJson> dialog = new Dialog<>();
    dialog.setTitle("Open File");

    Label saveLabel = new Label("Open file: ");
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(5);
    gridPane.add(saveLabel, 0, 0);
    TextField file = new TextField();
    gridPane.add(file, 1, 0);

    dialog.getDialogPane().setContent(gridPane);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.setResultConverter(param -> {
      if (param == ButtonType.OK) {
        WeekJson week =
            new WeekJson(days, maxEvents, maxTasks, quotesNotes.getText(), title.getText(),
                menuBar.getStyle(), mainvBox.getStyle());
        openFile(file.getText());
        return week;
      } else {
        return null;
      }
    });
    dialog.showAndWait();
  }

  /**
   * opens a .bujo file
   *
   * @param filename file name
   */
  public void openFile(String filename) {
    try {
      File file = new File(filename + ".bujo");
      StringBuilder data = new StringBuilder();
      Scanner input = new Scanner(file);
      while (input.hasNextLine()) {
        data.append(input.nextLine());
      }

      JsonNode node = new ObjectMapper().readTree(data.toString());
      WeekJson newWeek = new ObjectMapper().convertValue(node, WeekJson.class);

      this.days = newWeek.days();
      this.maxEvents = newWeek.maxEvents();
      this.maxTasks = newWeek.maxTasks();
      this.setup();

      this.quotesNotes.setText(newWeek.quotesAndNotes());
      this.title.setText(newWeek.title());

      mainvBox.setStyle(newWeek.mainVboxStyle());
      menuBar.setStyle(newWeek.menuBarStyle());
    } catch (IOException e) {
      System.err.println("Error while trying to open .bujo file: " + e);
    }
  }

  /**
   * makes a new blank .bujo file. (The old one is still in its own file)
   */
  private void handleNewWeek() {
    ArrayList<Day> weekdays = new ArrayList<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      weekdays.add(new Day(day));
    }

    ArrayList<ProgressBar> progressBars = new ArrayList<>(Arrays.asList(sunProgress,
        monProgress, tuesProgress, wedProgress, thursProgress, friProgress, satProgress));
    for (ProgressBar bar : progressBars) {
      bar.setProgress(0);
    }
    ArrayList<Label> labels = new ArrayList<>(Arrays.asList(sunProgressLabel,
        monProgressLabel, tuesProgressLabel, wedProgressLabel, thursProgressLabel,
        friProgressLabel, satProgressLabel));
    for (Label label : labels) {
      label.setText("");
    }

    this.days = weekdays;
    this.maxEvents = -1;
    this.maxTasks = -1;

    taskCounter = 0;
    eventCounter = 0;
    this.setup();
  }

  /**
   * Prompts a dialog so user can choose font
   */
  private void handleFontStyle() {
    Dialog<Font> dialog = new Dialog<>();
    dialog.setTitle("Font Style");
    HBox horizontalBox = new HBox();
    Label label = new Label("Choose font: ");
    ChoiceBox<String> choiceBox = new ChoiceBox<>();
    choiceBox.setMaxWidth(200);
    choiceBox.getItems().addAll("Default", "Times New Roman", "Comic Sans",
        "Castellar", "Britannic Bold", "Kristen", "Jokerman", "Century Gothic");
    horizontalBox.getChildren().addAll(label, choiceBox);
    horizontalBox.setAlignment(Pos.CENTER);
    horizontalBox.setSpacing(5);
    dialog.getDialogPane().setContent(horizontalBox);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

    dialog.setResultConverter(param -> {
      if (param == ButtonType.OK) {
        setFont(choiceBox);
        return Font.font("System");
      } else {
        return null;
      }
    });
    dialog.showAndWait();
  }

  /**
   * Prompts a dialog so user can customize the bullet journal theme
   */
  private void handleCustomTheme() {
    Dialog<Color> dialog = new Dialog<>();
    dialog.setTitle("Custom Theme");
    GridPane gridPane = new GridPane();
    gridPane.setHgap(20);
    gridPane.setVgap(10);
    ArrayList<Label> labels = new ArrayList<>(Arrays.asList(new Label("Background Color"),
        new Label("Menu Bar Color"),
        new Label("Sunday Color"), new Label("Monday Color"),
        new Label("Tuesday Color"), new Label("Wednesday Color"),
        new Label("Thursday Color"), new Label("Friday Color"),
        new Label("Saturday Color"), new Label("Title Color"),
        new Label("Tasks Color"), new Label("Notes Color"), new Label("Text Font")));
    for (int i = 0; i < labels.size(); i++) {
      gridPane.add(labels.get(i), 0, i);
    }
    ChoiceBox<String> fontChoice = new ChoiceBox<>();
    fontChoice.setMaxWidth(200);
    fontChoice.getItems().addAll("Default", "Times New Roman", "Comic Sans",
        "Castellar", "Century Gothic", "Kristen", "Jokerman", "Britannic Bold");
    for (int i = 0; i < labels.size() - 1; i++) {
      colorPickers.get(i).setMaxWidth(200);
      gridPane.add(colorPickers.get(i), 1, i);
    }
    gridPane.add(fontChoice, 1, labels.size() - 1);
    dialog.getDialogPane().setContent(gridPane);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.setResultConverter(param -> {
      if (param == ButtonType.OK) {
        Color menuBarColor = colorPickers.get(1).getValue();
        mainvBox.setStyle("-fx-background-color: "
            + colorPickers.get(0).getValue().toString().replace("0x", "#"));
        menuBar.setStyle("-fx-background-color: "
            + menuBarColor.toString().replace("0x", "#"));
        ArrayList<ProgressBar> progressBars = new ArrayList<>(Arrays.asList(sunProgress,
            monProgress, tuesProgress, wedProgress, thursProgress, friProgress, satProgress));
        for (ProgressBar bar : progressBars) {
          bar.setStyle("-fx-accent: "
              + menuBarColor.toString().replace("0x", "#"));
        }
        title.setEffect(new DropShadow(10, menuBarColor));
        menuBar.setEffect(new DropShadow(8, menuBarColor.brighter()));
        sundayVbox.setStyle("-fx-background-color: "
            + colorPickers.get(2).getValue().toString().replace("0x", "#"));
        mondayVbox.setStyle("-fx-background-color: "
            + colorPickers.get(3).getValue().toString().replace("0x", "#"));
        tuesdayVbox.setStyle("-fx-background-color: "
            + colorPickers.get(4).getValue().toString().replace("0x", "#"));
        wednesdayVbox.setStyle("-fx-background-color: "
            + colorPickers.get(5).getValue().toString().replace("0x", "#"));
        thursdayVbox.setStyle("-fx-background-color: "
            + colorPickers.get(6).getValue().toString().replace("0x", "#"));
        fridayVbox.setStyle("-fx-background-color: "
            + colorPickers.get(7).getValue().toString().replace("0x", "#"));
        saturdayVbox.setStyle("-fx-background-color: "
            + colorPickers.get(8).getValue().toString().replace("0x", "#"));
        title.setBackground(new Background(new BackgroundFill(
            colorPickers.get(9).getValue(), CornerRadii.EMPTY, Insets.EMPTY)));
        taskScrollPane.setStyle("-fx-background: "
            + colorPickers.get(10).getValue().toString().replace("0x", "#"));
        quotesNotes.lookup(".content").setStyle("-fx-background-color: "
            + colorPickers.get(11).getValue().toString().replace("0x", "#"));
        setFont(fontChoice);
        return Color.GRAY;
      } else {
        return null;
      }
    });
    dialog.showAndWait();
  }

  /**
   * Handles opening a template bujo
   */
  private void handleOpenTemplate() {
    Dialog<WeekJson> dialog = new Dialog<>();
    dialog.setTitle("Open File");

    Label saveLabel = new Label("File with template: ");
    Label nameLabel = new Label("Name of new week: ");
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(5);
    gridPane.add(saveLabel, 0, 0);
    TextField file = new TextField();
    gridPane.add(file, 1, 0);
    gridPane.add(nameLabel, 0, 1);
    TextField name = new TextField();
    gridPane.add(name, 1, 1);

    dialog.getDialogPane().setContent(gridPane);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.setResultConverter(param -> {
      if (param == ButtonType.OK) {
        WeekJson week =
            new WeekJson(days, maxEvents, maxTasks, quotesNotes.getText(), title.getText(),
                menuBar.getStyle(), mainvBox.getStyle());
        openTemplate(file.getText(), name.getText());
        return week;
      } else {
        return null;
      }
    });
    dialog.showAndWait();
  }

  private void openTemplate(String filename, String name) {
    try {
      File file = new File(filename + ".bujo");
      StringBuilder data = new StringBuilder();
      Scanner input = new Scanner(file);
      while (input.hasNextLine()) {
        data.append(input.nextLine());
      }

      JsonNode node = new ObjectMapper().readTree(data.toString());
      WeekJson newWeek = new ObjectMapper().convertValue(node, WeekJson.class);

      ArrayList<Day> emptyWeek = new ArrayList<>();
      for (DayOfWeek day : DayOfWeek.values()) {
        emptyWeek.add(new Day(day));
      }

      ArrayList<ProgressBar> progressBars = new ArrayList<>(Arrays.asList(sunProgress,
          monProgress, tuesProgress, wedProgress, thursProgress, friProgress, satProgress));
      for (ProgressBar bar : progressBars) {
        bar.setProgress(0);
      }
      ArrayList<Label> labels = new ArrayList<>(Arrays.asList(sunProgressLabel,
          monProgressLabel, tuesProgressLabel, wedProgressLabel, thursProgressLabel,
          friProgressLabel, satProgressLabel));
      for (Label label : labels) {
        label.setText("");
      }

      this.days = emptyWeek;

      this.maxEvents = newWeek.maxEvents();
      this.maxTasks = newWeek.maxTasks();

      taskCounter = 0;
      eventCounter = 0;
      this.setup();

      this.quotesNotes.setText(newWeek.quotesAndNotes());
      this.title.setText(name);

      mainvBox.setStyle(newWeek.mainVboxStyle());
      menuBar.setStyle(newWeek.menuBarStyle());
    } catch (IOException e) {
      System.err.println("Error while trying to open .bujo file: " + e);
    }
  }

  /**
   * sets the text font
   */
  private void setFont(ChoiceBox<String> choice) {
    Font font;
    switch (choice.getValue()) {
      case "Century Gothic" -> {
        title.setFont(Font.font("Century Gothic", FontWeight.BOLD, 20));
        font = Font.font("Century Gothic", FontWeight.BOLD, 16);
      }
      case "Times New Roman" -> {
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        font = Font.font("Times New Roman", FontWeight.BOLD, 16);
      }
      case "Comic Sans" -> {
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        font = Font.font("Comic Sans MS", FontWeight.BOLD, 16);
      }
      case "Castellar" -> {
        title.setFont(Font.font("Castellar", FontWeight.BOLD, 20));
        font = Font.font("Castellar", FontWeight.BOLD, 16);
      }
      case "Britannic Bold" -> {
        title.setFont(Font.font("Britannic Bold", FontWeight.BOLD, 20));
        font = Font.font("Britannic Bold", FontWeight.BOLD, 16);
      }
      case "Kristen" -> {
        title.setFont(Font.font("Kristen ITC", FontWeight.BOLD, 20));
        font = Font.font("Kristen ITC", FontWeight.BOLD, 16);
      }
      case "Default" -> {
        title.setFont(Font.font("System", FontWeight.BOLD, 20));
        font = Font.font("System", FontWeight.BOLD, 16);
      }
      default -> {
        title.setFont(Font.font("Jokerman", FontWeight.BOLD, 20));
        font = Font.font("Jokerman", FontWeight.BOLD, 16);
      }
    }
    tasksLabel.setFont(font);
    quotesLabel.setFont(font);
    sunLabel.setFont(font);
    monLabel.setFont(font);
    tuesLabel.setFont(font);
    wedsLabel.setFont(font);
    thursLabel.setFont(font);
    friLabel.setFont(font);
    satLabel.setFont(font);
    quotesNotes.setFont(font);
  }
}
