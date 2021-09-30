#include <iostream>

using namespace std;
    
class Parent
{
    public:
        void setWidth(int w)
        {
            width = w;
        }
        void setHeight(int h)
        {
            height = h;
        }
        // 方法写在 protected 中好像不方便调用
        int getRound()
        {
            return 2 * (width + height);
        }
    protected:
        int width;
        int height;
};

class Child: public Parent
{
    public:
        int getArea()
        {
            return (width *  height);
        }
};

int main(void) {
    Child chd;

    chd.setWidth(3);
    chd.setHeight(4);

    cout << "Total area: " << chd.getArea() << endl;
    cout << "Total round: " << chd.getRound() << endl;
}
