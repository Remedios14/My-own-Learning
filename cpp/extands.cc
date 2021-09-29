#include <iostream>

using namespace std;

class Shape 
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
   protected:
      int width;
      int height;
};
 
// 派生类
class Rectangle: public Shape
{
   public:
      int getArea()
      { 
         return (width * height); 
      }
        void output(){
            update();
            cout << "Current area : " << getArea() << endl;
        }

    private:
        void update()
        {
            setWidth(5);
            setHeight(6);
        }
};
 
int main(void)
{
   Rectangle Rect;
 
   Rect.setWidth(5);
   Rect.setHeight(7);
 
   // 输出对象的面积
   cout << "Total area: " << Rect.getArea() << endl;
   Rect.output();
 
   return 0;
}
