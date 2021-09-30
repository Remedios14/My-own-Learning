#include <iostream>

using namespace std;

int ex(int* int_ptr) {
    int exchange = 5;
    *int_ptr = exchange;
    cout << "指针值是否已修改 " << *int_ptr << endl;
    return exchange;
}

int main() {
    int ci;
    cout << "指针初始化 " << ci << endl;
    int now = ex(&ci);
    cout << "在外面观察指针 " << ci << endl;
    cout << "修改值 " << now << endl;
    return 1;
}