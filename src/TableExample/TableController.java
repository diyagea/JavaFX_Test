package TableExample;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class TableController implements Initializable {
	@FXML
	private Label fxLabel;
	@FXML
	private TableView<Student> stuTable;
	@FXML
	private TableColumn<Student, String> idCol, fNameCol, lNameCol, delCol;
	@FXML
	private TableColumn<Student, Integer> ageCol;
	@FXML
	private TableColumn<Student, Boolean> isAdultCol;
	
	private Set<String> selectSet;

	public ObservableList<Student> getStuData() {
		selectSet = new HashSet<>();

		Student stu1 = new Student("��", "��", 16);
		Student stu2 = new Student("Ǯ", "��", 26);
		Student stu3 = new Student("��", "��", 23);
		Student stu4 = new Student("��", "��ɽ", 17);
		Student stu5 = new Student("��", "������", 23);
		Student stu6 = new Student("��", "����", 12);
		Student stu7 = new Student("֣", "��", 28);
		Student stu8 = new Student("��", "��", 23);
		Student stu9 = new Student("��", "��", 15);
		Student stu10 = new Student("��", "ʱ��", 23);
		Student stu11 = new Student("��", "����", 19);
		Student stu12 = new Student("���", "��", 23);
		Student stu13 = new Student("˾��", "����", 20);

		ObservableList<Student> stuLists = FXCollections.observableArrayList(stu1, stu2, stu3, stu4, stu5, stu6, stu7, stu8, stu9, stu10, stu11, stu12, stu13);
		return stuLists;
	}

	/**
	 * ��ʾѧ�����
	 * 
	 * @param stuLists
	 */
	public void showStuTable(ObservableList<Student> stuLists) {
		fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		// ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		idCol.setCellFactory((col) -> {
			TableCell<Student, String> cell = new TableCell<Student, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					this.setText(null);
					this.setGraphic(null);

					if (!empty) {
						int rowIndex = this.getIndex() + 1;
						this.setText(String.valueOf(rowIndex));
					}
				}
			};
			return cell;
		});

		ageCol.setCellFactory((col) -> {
			TableCell<Student, Integer> cell = new TableCell<Student, Integer>() {

				@Override
				public void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty);
					this.setText(null);
					this.setGraphic(null);

					if (!empty) {
						int age = this.getTableView().getItems().get(this.getIndex()).getAge();
						this.setText(String.valueOf(age));
						if (age < 18) {
							this.getStyleClass().add("mark");
						}
					}
				}

			};
			return cell;
		});

		isAdultCol.setCellFactory((col) -> {
			TableCell<Student, Boolean> cell = new TableCell<Student, Boolean>() {

				@Override
				public void updateItem(Boolean item, boolean empty) {
					super.updateItem(item, empty);
					this.setText(null);
					this.setGraphic(null);

					if (!empty) {
						CheckBox checkBox = new CheckBox();
						this.setGraphic(checkBox);
						checkBox.selectedProperty().addListener((obVal, oldVal, newVal) -> {
							String firstName = this.getTableView().getItems().get(this.getIndex()).getFirstName();
							String lastName = this.getTableView().getItems().get(this.getIndex()).getLastName();
							int line = this.getIndex() + 1;
							if (newVal) {
								// ���ѡ��ʱִ�еĴ���
								System.out.println("��" + line + "�б�ѡ�У�");
								selectSet.add(firstName+lastName);
							}else{
								System.out.println("��" + line + "�б�ȡ����");
								selectSet.remove(firstName+lastName);
								
							}

						});
					}
				}

			};
			return cell;
		});

		delCol.setCellFactory((col) -> {
			TableCell<Student, String> cell = new TableCell<Student, String>() {

				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					this.setText(null);
					this.setGraphic(null);

					if (!empty) {
						//ImageView delICON = new ImageView(getClass().getResource("delete.png").toString());
//						Button delBtn = new Button("ɾ��", delICON);
						Button delBtn = new Button("ɾ��");
						this.setGraphic(delBtn);
						delBtn.setOnMouseClicked((me) -> {
							Student clickedStu = this.getTableView().getItems().get(this.getIndex());
							System.out.println("ɾ�� " + clickedStu.getFirstName() + clickedStu.getLastName() + " �ļ�¼");
						});
					}
				}

			};
			return cell;
		});

		stuTable.setItems(stuLists);
	}
	
	@FXML
	public void selectData(){
		if(this.selectSet.size()==0){
			System.out.println("δѡ���κ����ݣ�");
		}
		for(String s : this.selectSet){
			System.out.println(s);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		fxLabel.setWrapText(true);
		fxLabel.setText("javafx_Label��ǩ�Ļ��в��ԣ����ڵ��������ʾ��Ϣ���ԣ�Label�ؼ�Ҳ���Ի��С�");

		this.showStuTable(this.getStuData());
	}

}