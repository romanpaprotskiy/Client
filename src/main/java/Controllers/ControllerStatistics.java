package Controllers;

import DAO.CustomDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ControllerStatistics extends Controller {


    @FXML
    public PieChart stat;

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
        try {
            CustomDAO customDAO = new CustomDAO(connection);
            ObservableList<PieChart.Data> data =
                    FXCollections.observableArrayList(
                            new PieChart.Data("усне", customDAO.countUs()),
                            new PieChart.Data("письмове", customDAO.countP()),
                            new PieChart.Data("економічно-правое", customDAO.countEcon()),
                            new PieChart.Data("забезпечення", customDAO.countZ()),
                            new PieChart.Data("семінар", customDAO.countSem()),
                            new PieChart.Data("аудит", customDAO.countAu()));
            stat.setData(data);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void CompleteClick(MouseEvent mouseEvent) {
        try {
            CustomDAO customDAO = new CustomDAO(connection);
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
