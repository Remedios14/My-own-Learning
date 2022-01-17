public class Decorator {
    public static void main(String[] args) {
        TextNode n1 = new SpanNode();
        TextNode n2 = new BoldDecorator(new UnderlineDecorator(new SpanNode()));
        TextNode n3 = new ItalicDecorator(new BoldDecorator(new SpanNode()));
        n1.setText("Hello");
        n2.setText("Decorated");
        n3.setText("World");
        System.out.println(n1.getText());
        // 输出<span>Hello</span>

        System.out.println(n2.getText());
        // 输出<b><u><span>Decorated</span></u></b>

        System.out.println(n3.getText());
        // 输出<i><b><span>World</span></b></i>
    }
}

// 顶层接口
interface TextNode {
    // 设置 text:
    void setText(String text);
    // 获取 text:
    String getText();
}

// 核心节点
class SpanNode implements TextNode {
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return "<span>" + text + "</span>";
    }
}

// 一个抽象的 Decorator 类
abstract class NodeDecorator implements TextNode {
    // 持有一个 TextNode 对象
    protected final TextNode target;

    protected NodeDecorator(TextNode target) {
        this.target = target;
    }

    public void setText(String text) {
        this.target.setText(text);
    }
}

class BoldDecorator extends NodeDecorator {
    public BoldDecorator(TextNode target) {
        super(target);
    }

    public String getText() {
        return "<b>" + target.getText() + "</b>";
    }
}

class ItalicDecorator extends NodeDecorator {
    public ItalicDecorator(TextNode target) {
        super(target);
    }

    public String getText() {
        return "<i>" + target.getText() + "</i>";
    }
}

class UnderlineDecorator extends NodeDecorator {
    public UnderlineDecorator(TextNode target) {
        super(target);
    }

    public String getText() {
        return "<u>" + target.getText() + "</u>";
    }
}
