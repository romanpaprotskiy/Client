package Controllers;

import DAO.CustomDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class ControllerStatistics extends Controller {

    @FXML
    public PieChart stat;
    @FXML
    public AnchorPane stats;
    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    private CustomDAO customDAO;
    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private final BarChart<String, Number> chart =
            new BarChart<String, Number>(xAxis, yAxis);

    public void initialize() {
        try {
            customDAO = new CustomDAO(connection);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void CustomerClick(MouseEvent mouseEvent) {
        primaryStage.setScene(scene3);
    }

    @FXML
    public void EmployeeClick(MouseEvent mouseEvent) {
        primaryStage.setScene(scene2);
    }

    @FXML
    public void ExitApp(MouseEvent mouseEvent) {
        ExitWin();
    }

    @FXML
    public void CustomsClick(MouseEvent mouseEvent) {
        primaryStage.setScene(scene1);
    }

    @FXML
    public void ProfitClick(MouseEvent mouseEvent) {
        if (stats.getChildren().contains(stat)) stats.getChildren().remove(stat);
        if (!stats.getChildren().contains(chart)) stats.getChildren().add(chart);
        chart.setTitle("Статистика замовлень по місяцях поточного року");
        xAxis.setLabel("Місяць");
        yAxis.setLabel("Кількість замовлень");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        try {
            series.getData().add(new XYChart.Data<String, Number>("Січень", customDAO.getCountCustom("01")));
            series.getData().add(new XYChart.Data<String, Number>("Лютий", customDAO.getCountCustom("02")));
            series.getData().add(new XYChart.Data<String, Number>("Березень", customDAO.getCountCustom("03")));
            series.getData().add(new XYChart.Data<String, Number>("Квітень", customDAO.getCountCustom("04")));
            series.getData().add(new XYChart.Data<String, Number>("Травень", customDAO.getCountCustom("05")));
            series.getData().add(new XYChart.Data<String, Number>("Червень", customDAO.getCountCustom("06")));
            series.getData().add(new XYChart.Data<String, Number>("Липень", customDAO.getCountCustom("07")));
            series.getData().add(new XYChart.Data<String, Number>("Серпень", customDAO.getCountCustom("08")));
            series.getData().add(new XYChart.Data<String, Number>("Вересень", customDAO.getCountCustom("09")));
            series.getData().add(new XYChart.Data<String, Number>("Жовтень", customDAO.getCountCustom("10")));
            series.getData().add(new XYChart.Data<String, Number>("Листопад", customDAO.getCountCustom("11")));
            series.getData().add(new XYChart.Data<String, Number>("Грудень", customDAO.getCountCustom("12")));
            series1.getData().add(new XYChart.Data<String, Number>("Січень", customDAO.getFinishedCustom("01")));
            series1.getData().add(new XYChart.Data<String, Number>("Лютий", customDAO.getFinishedCustom("02")));
            series1.getData().add(new XYChart.Data<String, Number>("Березень", customDAO.getFinishedCustom("03")));
            series1.getData().add(new XYChart.Data<String, Number>("Квітень", customDAO.getFinishedCustom("04")));
            series1.getData().add(new XYChart.Data<String, Number>("Травень", customDAO.getFinishedCustom("05")));
            series1.getData().add(new XYChart.Data<String, Number>("Червень", customDAO.getFinishedCustom("06")));
            series1.getData().add(new XYChart.Data<String, Number>("Липень", customDAO.getFinishedCustom("07")));
            series1.getData().add(new XYChart.Data<String, Number>("Серпень", customDAO.getFinishedCustom("08")));
            series1.getData().add(new XYChart.Data<String, Number>("Вересень", customDAO.getFinishedCustom("09")));
            series1.getData().add(new XYChart.Data<String, Number>("Жовтень", customDAO.getFinishedCustom("10")));
            series1.getData().add(new XYChart.Data<String, Number>("Листопад", customDAO.getFinishedCustom("11")));
            series1.getData().add(new XYChart.Data<String, Number>("Грудень", customDAO.getFinishedCustom("12")));
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
        series.setName("Замовлення");
        series1.setName("Виконані замовлення");
        chart.getData().clear();
        chart.getData().add(series);
        chart.getData().add(series1);
    }

    @FXML
    public void CompleteClick(MouseEvent mouseEvent) {
        if (stats.getChildren().contains(chart)) stats.getChildren().remove(chart);
        if (!stats.getChildren().contains(stat)) stats.getChildren().add(stat);
        try {
            stat.setTitle("Статистика виконання замовлень");
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Виконано", customDAO.completeCustom()),
                            new PieChart.Data("Затверджено", customDAO.appliedCustom()),
                            new PieChart.Data("Не виконано", customDAO.allCustom()));
            stat.setData(pieChartData);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }
}
