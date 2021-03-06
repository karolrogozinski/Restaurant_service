from typing import List, Dict, Tuple, Union
from random import choice, randint, sample
import lorem  # type: ignore

EMPLOYEE_KINDS: List[str] = ['waiter', 'cook']


class EmployeeKind:
    def __init__(self, id: int, kind: str) -> None:
        self.id = id
        self.kind = kind

    def __str__(self) -> str:
        return f'INSERT INTO employee_kinds VALUES ({self.id}, \'{self.kind}\');\n'

    @staticmethod
    def generate_employee_kinds() -> List['EmployeeKind']:
        kinds: List['EmployeeKind'] = []
        for i, kind in enumerate(EMPLOYEE_KINDS, start=1):  # type: ignore
            kinds.append(EmployeeKind(i, kind))  # type: ignore
        return kinds


EMPLOYEES = 10

FIRST_NAMES = ['Adam', 'Bartlomiej', 'Kamil', 'Karol']
LAST_NAMES = ['Krawczyk', 'Sudol', 'Sulkowski', 'Rogozinski']


class Employee:
    i: int = 1

    def __init__(self, name: str, last_name: str, employee_kind: int) -> None:
        self.id = Employee.i
        Employee.i += 1
        self.name = name
        self.last_name = last_name
        self.employee_kind = employee_kind

    def __str__(self) -> str:
        return f'INSERT INTO employees VALUES ({self.id}, \'{self.name}\', \'{self.last_name}\', {self.employee_kind});\n'

    @staticmethod
    def generate_employees(kinds: List[EmployeeKind]) -> List['Employee']:
        employees: List['Employee'] = []
        i = 0
        for kind in kinds:
            i += 1
            for _ in range(randint(EMPLOYEES // 2, EMPLOYEES)):
                employees.append(
                    Employee(choice(FIRST_NAMES), choice(LAST_NAMES), kind.id))
        return employees


TABLES = ['Near the Window', 'Near the Doors', 'Over the Bar']


class Table:
    def __init__(self, id: int, name: str) -> None:
        self.id = id
        self.name = name

    def __str__(self) -> str:
        return f'INSERT INTO tables VALUES ({self.id}, \'{self.name}\');\n'

    @staticmethod
    def generate_tables() -> List['Table']:
        tables: List['Table'] = []
        for i, name in enumerate(TABLES, start=1):  # type: ignore
            tables.append(Table(i, name))  # type: ignore
        return tables


PRODUCT_CATEGORIES = ['Drinks', 'Meat', 'Vegetables', 'Fruits', 'Dairy']


class ProductCategory:
    def __init__(self, id: int, name: str) -> None:
        self.id = id
        self.name = name

    def __str__(self) -> str:
        return f'INSERT INTO product_categories VALUES ({self.id}, \'{self.name}\');\n'

    @staticmethod
    def generate_product_categories() -> List['ProductCategory']:
        categories: List['ProductCategory'] = []
        for i, category in enumerate(PRODUCT_CATEGORIES, start=1):  # type: ignore
            categories.append(ProductCategory(i, category))  # type: ignore
        return categories


class Product:
    i = 1

    def __init__(self, name: str, quantity: int, min_quantity: int, unit: str, category: int) -> None:
        self.id = Product.i
        Product.i += 1
        self.name = name
        self.quantity = quantity
        self.min_quantity = min_quantity
        self.unit = unit
        self.category = category

    def __str__(self) -> str:
        return f'INSERT INTO products VALUES ({self.id}, \'{self.name}\', {self.quantity}, {self.min_quantity}, \'{self.unit}\', {self.category});\n'

    @staticmethod
    def generate_products() -> List['Product']:
        return PRODUCTS


PRODUCTS: List['Product'] = [
    Product('Coca-Cola 500ml', 999999, 100, 'bottle', 1),
    Product('Coca-Cola 1l', 999999, 50, 'bottle', 1),
    Product('Sprite 500ml', 999999, 50, 'bottle', 1),
    Product('Fanta 500ml', 999999, 50, 'bottle', 1),
    Product('Chicken Breasts', 999999, 10000, 'g', 2),
    Product('Chicken Wings', 999999, 10000, 'g', 2),
    Product('Beef', 999999, 10000, 'g', 2),
    Product('Pork Chop', 999999, 10000, 'g', 2),
    Product('Onions', 999999, 1000, 'g', 3),
    Product('Pepper', 999999, 1000, 'g', 3),
    Product('Carrot', 999999, 1000, 'g', 3),
    Product('Apple', 999999, 100, 'fruit', 4)
]

DISHES: Dict[str, List[Tuple[str, str]]] = {
    'Soups': [
        ('Tomato', '\'tomato.jpg\''),
        ('Chicken',  '\'chicken.jpg\''),
        ('Pumpkin', '\'pumpkin.jpg\''),
        ('Cream', '\'cream.jpg\''),
        ('Carrot', '\'carrot.jpg\'')
    ],
    'Main Course': [
        ('Beef', '\'beef.jpg\''),
        ('Hamburger', '\'hamburger.jpg\''),
        ('Spaghetti', '\'spaghetti.jpg\''),
        ('Pizza', '\'pizza.jpg\''),
    ],
    'Drinks': [
        ('Coca-Cola', '\'coca-cola.jpg\''),
        ('Fanta', '\'fanta.jpg\''),
        ('Sprite', '\'sprite.jpg\''),
        ('Beer', '\'beer.jpg\''),
    ]
}


class DishCategory:
    def __init__(self, id: int, name: str, dishes: List[Tuple[str, str]]) -> None:
        self.id = id
        self.name = name
        self.dishes = [Dish(name[0], self.id, name[1]) for name in dishes]

    def __str__(self) -> str:
        result = f'\nINSERT INTO dish_categories VALUES ({self.id}, \'{self.name}\', NULL);\n'
        for dish in self.dishes:
            result += str(dish)
        return result

    @staticmethod
    def generate_categories() -> List['DishCategory']:
        return [DishCategory(i, name, DISHES[name]) for i, name in enumerate(DISHES.keys(), start=1)]


class Dish:
    i: int = 1

    def __init__(self, name: str, category: int, image_path: str = 'NULL') -> None:
        self.id = Dish.i
        Dish.i += 1
        self.name = name
        self.image_path = image_path
        self.price = randint(1000, 10000)
        self.category = category
        self.recipe = [Recipe(i, self.id) for i in range(1, randint(2, 10))]
        self.ingredients = [Ingredient(self.id, product)
                            for product in sample(PRODUCTS, randint(2, 10))]

    def __str__(self) -> str:
        result = f'\nINSERT INTO dishes VALUES ({self.id}, \'{self.name}\', {self.image_path}, {self.price}, \'{self.category}\');\n\n'
        for recipe in self.recipe:
            result += str(recipe)
        result += '\n'
        for ingredient in self.ingredients:
            result += str(ingredient)
        return result


class Ingredient:
    i: int = 1

    def __init__(self, dish: int, product: Product) -> None:
        self.id = Ingredient.i
        Ingredient.i += 1
        self.quantity = randint(1, 100)
        self.dish = dish
        self.product = product.id

    def __str__(self) -> str:
        return f'INSERT INTO ingredients VALUES ({self.id}, {self.quantity}, {self.dish}, {self.product});\n'


class Recipe:
    def __init__(self, step: int, dish: int) -> None:
        self.step = step
        self.recipe: str = lorem.sentence()  # type: ignore
        self.dish = dish

    def __str__(self) -> str:
        return f'INSERT INTO recipes VALUES ({self.step}, \'{self.recipe}\', {self.dish});\n'


class Receipt:
    i: int = 1

    def __init__(self, waiter: int, table: int, dishes: List[Dish], cooks: List[Employee]) -> None:
        self.id = Receipt.i
        Receipt.i += 1
        self.payment = 0
        self.employee = waiter
        self.table = table
        self.orders = [Order(choice(dishes).id, self.id, choice(cooks))
                       for _ in range(randint(1, 10))]

    def __str__(self) -> str:
        result = f'INSERT INTO receipts VALUES ({self.id}, {self.payment}, {self.employee}, {self.table});\n\n'
        for order in self.orders:
            result += str(order)
        result += '\n'
        return result

    @staticmethod
    def generate_receipts(employees: List[Employee], tables: List[Table], dishes: List[Dish]) -> List['Receipt']:
        waiters = [
            employee for employee in employees if employee.employee_kind == 1]
        cooks = [
            employee for employee in employees if employee.employee_kind == 2]
        receipts: List[Receipt] = []
        for waiter in waiters:
            receipts.extend(
                [Receipt(waiter.id, table.id, dishes, cooks) for table in tables])
        return receipts


class Order:
    i: int = 1

    def __init__(self, dish: int, receipt: int, cook: Employee) -> None:
        self.id = Order.i
        Order.i += 1
        self.date = randint(1140693898347, 1640693898347)
        self.dish = dish
        self.receipt = receipt
        self.status = randint(1, 3)
        if self.status == 1:
            self.employee_id: Union[str, int] = choice(['NULL', cook.id])
        else:
            self.employee_id = cook.id

    def __str__(self) -> str:
        return f'INSERT INTO orders VALUES ({self.id}, {self.date}, {self.dish}, {self.receipt}, {self.status}, {self.employee_id});\n'
