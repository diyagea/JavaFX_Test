package graphics;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class FX3DMesh extends Application {
        
        private Scene scene;
        private int sceneWidth = 600;
        private int sceneHeight = 600;
        PerspectiveCamera camera;
        Group pyramidGroup;
        private double sceneX, sceneY=0;
        private double fixedXAngle, fixedYAngle=0;
        private final DoubleProperty angleX = new SimpleDoubleProperty(0);
        private final DoubleProperty angleY = new SimpleDoubleProperty(0);

        
        @Override
        public void start(Stage primaryStage) {
                
                Group root = new Group();
                scene = new Scene( root, sceneWidth, sceneHeight);
                scene.setFill(Color.BLACK);
                
                camera = new PerspectiveCamera(true);
                camera.setNearClip(.1);                                                                // true in camera constructor determines center of pane as origin, all axis are 0,0,0 there 
                camera.setFarClip(10000.0);                                                        // false moves it to top left corner
                camera.setTranslateZ(-1000);
                scene.setCamera(camera);
                
                PointLight light = new PointLight();                //add a pointlight to show highlights
                root.getChildren().add(light);
                light.setTranslateZ(-sceneWidth);
                light.setTranslateY(-sceneHeight);
                
                Group pyramid1 = buildPyramid(100,200,Color.GOLDENROD,true, false);
                pyramid1.setTranslateX(-100);        //closer to camera
                
                Group pyramid2 = buildPyramid(100,200,Color.GOLD,true, true);
                pyramid2.setTranslateX(-100);
                pyramid2.setTranslateY(-100);
                pyramid2.setRotationAxis(Rotate.Z_AXIS);
                pyramid2.setRotate(180);
                
                Group pyramid3 = buildPyramid(100,200,Color.LAWNGREEN,true, true);
                pyramid3.setTranslateX(100);
                
                Group pyramid4 = buildPyramid(100,200,Color.GREEN,true,false);
                pyramid4.setTranslateX(100);
                pyramid4.setTranslateY(-100);
                pyramid4.setRotationAxis(Rotate.Z_AXIS);
                pyramid4.setRotate(180);
                
                pyramidGroup = new Group();
                pyramidGroup.getChildren().addAll(pyramid1, pyramid2, pyramid3, pyramid4);
                
                hookupRotate();
                
                root.getChildren().addAll(pyramidGroup);
                primaryStage.setTitle("meshes and similar fun");
                primaryStage.setScene(scene);
                primaryStage.show();
        }
        
        private Group buildPyramid(
                        float height,
                        float hypotenuse,
                        Color color,
                        boolean ambient,
                        boolean fill
                        ){
                
                TriangleMesh mesh = new TriangleMesh();
                
                mesh.getPoints().addAll(
                                0,0,0,                                                //point 0
                                0,height,-hypotenuse/2,                // point 1
                                -hypotenuse/2,height,0,                // point 2
                                hypotenuse/2,height,0,                // point 3
                                0,height,hypotenuse/2                // point 4
                                );
                
                mesh.getTexCoords().addAll(0,0);
                mesh.getFaces().addAll(
                                0,0,2,0,1,0,        // vertical faces, wind counterclockwise
                                0,0,1,0,3,0,
                                0,0,3,0,4,0,
                                0,0,4,0,2,0,
                                4,0,1,0,2,0,        // base triangle 1, wind counterclockwise because camera has rotated
                                4,0,3,0,1,0
                                );
                MeshView meshView = new MeshView(mesh);
                
                meshView.setDrawMode(DrawMode.LINE);        // show lines only by default
                meshView.setCullFace(CullFace.BACK);        // Removing culling to show backlines
                
                Group pyramidGroup = new Group();
                pyramidGroup.getChildren().add(meshView);
                
                if(color != null){
                        PhongMaterial material = new PhongMaterial(color);
                        meshView.setMaterial(material);
                }
                
                if (ambient==true){
                        AmbientLight light = new AmbientLight(Color.WHITE);
                        light.getScope().add(meshView);
                        pyramidGroup.getChildren().add(light);
                }
                
                if (fill==true){
                        meshView.setDrawMode(DrawMode.FILL);
                }
                return pyramidGroup;
        }
        
        private void hookupRotate(){
                Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
                Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
                pyramidGroup.getTransforms().addAll(xRotate, yRotate);
                xRotate.angleProperty().bind(angleX);
                yRotate.angleProperty().bind(angleY);
                
                scene.setOnMousePressed(event -> {
                        sceneX = event.getSceneX();
                        sceneY = event.getSceneY();
                        fixedXAngle = angleX.get();
                        fixedYAngle = angleY.get();
                });
                
                scene.setOnMouseDragged(event ->{
                        angleX.set(fixedXAngle - (sceneX - event.getSceneY()));
                        angleY.set(fixedYAngle + (sceneY - event.getSceneX()));
                });
                
        }

        
        public static void main(String[] args) {
                launch(args);
        }
}