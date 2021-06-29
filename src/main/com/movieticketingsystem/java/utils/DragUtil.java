package main.com.movieticketingsystem.java.utils;

import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * @Author GirtSeanking
 * @Description //TODO 工具类，窗口移动
 * @Date 19:28
 **/
public class DragUtil {

    public static void addDragListener(Stage stage, Node root) {
        new DragListener(stage).enableDrag(root);
    }

}
