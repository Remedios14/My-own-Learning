package factoryp;

import java.io.IOException;
import java.nio.file.Path;

public class AbstractFactory {
    public static void main(String[] args) {
        
    }
}

// 一个总接口，内含导向不同产品接口的方法
interface AbstractDocsFactory {
    // 创建 Html 文档
    HtmlDocument createHtml(String md);
    // 创建 Word 文档
    WordDocument createWord(String md);
    // 获取工厂
    public static AbstractDocsFactory createFactory(String name) {
        if (name.equalsIgnoreCase("fast")) {
            return new FastFactory();
        } else if (name.equalsIgnoreCase("good")) {
            return new GoodFactory();
        } else {
            throw new IllegalArgumentException("Invalid factory name");
        }
    }
}

// Html文档接口:
interface HtmlDocument {
    String toHtml();
    void save(Path path) throws IOException;
}

// Word文档接口:
interface WordDocument {
    void save(Path path) throws IOException;
}

class FastHtmlDocument implements HtmlDocument {
    FastHtmlDocument(String s) {
        this.s = s;
    }
    public String toHtml() {
        return "Html";
    }
    public void save(Path path) throws IOException {
        return;
    }
    private String s;
}

class FastWordDocument implements WordDocument {
    FastWordDocument(String s) {
        this.s = s;
    }
    public String toWord() {
        return "Word";
    }
    public void save(Path path) throws IOException {
        return;
    }
    String s;
}

// 提供接口来生产两种产品
class FastFactory implements AbstractDocsFactory {
    public HtmlDocument createHtml(String md) {
        return new FastHtmlDocument(md);
    }
    public WordDocument createWord(String md) {
        return new FastWordDocument(md);
    }
}

class GoodHtmlDocument implements HtmlDocument {
    GoodHtmlDocument(String s) {
        this.s = s;
    }
    public String toHtml() {
        return "Html";
    }
    public void save(Path path) throws IOException {
        return;
    }
    private String s;
}

class GoodWordDocument implements WordDocument {
    GoodWordDocument(String s) {
        this.s = s;
    }
    public String toWord() {
        return "Word";
    }
    public void save(Path path) throws IOException {
        return;
    }
    String s;
}

class GoodFactory implements AbstractDocsFactory {
    @Override
    public HtmlDocument createHtml(String md) {
        // TODO Auto-generated method stub
        return new GoodHtmlDocument(md);
    }
    @Override
    public WordDocument createWord(String md) {
        // TODO Auto-generated method stub
        return new GoodWordDocument(md);
    }
}