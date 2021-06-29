package main.com.movieticketingsystem.java.utils;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @Author GirtSeanking
 * @Description //TODO 窗口移动的事件监听
 * @Date 19:29
 **/
public class DragListener implements EventHandler<MouseEvent> {

    /** 鼠标位置x值 */
    private double xOffset = 0;

    /** 鼠标位置y值 */
    private double yOffset = 0;

    /** 当前stage */
    private final Stage stage;

    /**
     * @Author GirtSeanking
     * @Description //TODO 获取需要监听的stage
     * @Date 19:30
     * @Param [stage]
     **/
    public DragListener(Stage stage) {
        this.stage = stage;
    }

    /**
     * @Author GirtSeanking
     * @Description //TODO 鼠标事件监听与窗口移动
     * @Date 19:31
     * @Param [mouseEvent]
     * @return void
     **/

    @Override
    public void handle(MouseEvent mouseEvent) {
        mouseEvent.consume();
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
            /* 获取当前鼠标位置 */
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            /* 窗口移动计算 */
            stage.setX(mouseEvent.getScreenX() - xOffset);
            if (mouseEvent.getScreenY() - yOffset < 0) {
                stage.setY(0);
            } else {
                stage.setY(mouseEvent.getScreenY() - yOffset);
            }
        }
    }

    /**
     * @Author GirtSeanking
     * @Description //TODO 事件监听驱动
     * @Date 19:31
     * @Param [node]
     * @return void
     **/
    public void enableDrag(Node node) {
        node.setOnMousePressed(this);
        node.setOnMouseDragged(this);
    }
}
