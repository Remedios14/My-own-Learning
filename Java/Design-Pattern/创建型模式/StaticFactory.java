package factoryp;

public class StaticFactory {
    public static void main(String[] args) {
        var factory = CardFactory.getFactory();
        var card = factory.pick();
        System.out.println(card);
    }
}

// 先有一个工厂接口
// 此时只需要知道接口并调用 getFactory() 方法即可
interface CardFactory {
    Card pick();

    // 静态方法获取工厂实例
    static CardFactory getFactory() {
        return impl;
    }

    static CardFactoryImpl impl = new CardFactoryImpl();
}

// 再编写一个工厂的实现类
// 具体的创建方法可以在此类中单独地修改
class CardFactoryImpl implements CardFactory {
    public Card pick() {
        return new Card(1);
    }
}

class Card {
    private int num;
    Card(int num) {
        this.num = num;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "the card number is " + this.num;
    }
}