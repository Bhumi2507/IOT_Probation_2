# 🧾 Basic Bill Calculator App

A simple and efficient mobile application designed to calculate bills with multiple items, automatically handle taxes and tips, and maintain a history of paid transactions.



## 📋 Table of Contents
- [Features](#features)
- [Screens & Functionality](#screens--functionality)
- [Calculation Logic](#calculation-logic)
- [Installation](#installation)
- [Usage](#usage)
- [Tech Stack](#tech-stack)

## ✨ Features
* **Dynamic Item Entry:** Add multiple items with name, price, and quantity.
* **Real-time Subtotals:** View the cost of items before tax/tip.
* **Automatic Tax & Tip:** Automatically calculates a 5% GST and 10% Tip on the final bill.
* **Transaction History:** Saves paid bills to a history list.
* **Expandable Details:** History cards can be expanded to view specific items purchased in that transaction.
* **Auto-Reset:** Automatically clears inputs for a fresh start after a bill is marked as paid.

## 📱 Screens & Functionality

### 1. Home Screen
The main interface for data entry and calculation.
* **Input Fields:** Three text fields for **Item Name**, **Price**, and **Quantity**.
* **Subtotal Display:** Shows the sum of all added items ($Price \times Quantity$).
* **Add New Item Button:** Adds the current item to the list, updates the subtotal, and clears the input fields for the next entry.
* **Generate Bill Button:** Calculates the final payable amount including tax and tip.
* **Paid Toggle:** Marks the transaction as complete, moves the data to the History screen, and resets the Home screen (clears inputs, subtotal, and total) for a new customer.

### 2. History Screen

A record of all completed transactions.
* **Bill Cards:** Displays the `Subtotal` and `Total` amount for every paid transaction.
* **Expand/Collapse:** Each card features an expand icon. Clicking this reveals the detailed list of items (names and quantities) included in that specific bill.

## 🧮 Calculation Logic
The app uses a specific formula to ensure the `Total Bill` is distinct from the `Subtotal`.

**Formulas used:**
$$Subtotal = \sum (ItemPrice \times Quantity)$$
$$GST = Subtotal \times 0.05$$
$$Tip = Subtotal \times 0.10$$
$$Total = Subtotal + GST + Tip$$

## 🛠️ Tech Stack
*(Please uncomment and fill in the specific technologies you used, e.g., React Native, Flutter, Swift, Kotlin, etc.)*

* **Framework:** [e.g., React Native / Flutter]
* **Language:** [e.g., JavaScript / Dart]
* **State Management:** [e.g., Context API / Provider / Redux]
* **Navigation:** [e.g., React Navigation]

## 🚀 Installation

1.  **Clone the repository**
    ```bash
    git clone [https://github.com/yourusername/bill-calculator-app.git](https://github.com/yourusername/bill-calculator-app.git)
    ```

2.  **Navigate to the project directory**
    ```bash
    cd bill-calculator-app
    ```

3.  **Install dependencies**
    ```bash
    npm install
    # or
    flutter pub get
    ```

4.  **Run the application**
    ```bash
    npm start
    # or
    flutter run
    ```

## 📖 Usage Guide

1.  Enter the **Item Name**, **Price**, and **Quantity** in the input fields.
2.  Tap **"Add New Item"**. The subtotal will update, and fields will clear.
3.  Repeat step 1 & 2 for all items in the cart.
4.  Tap **"Generate Bill"** to see the final amount (inclusive of 5% GST and 10% Tip).
5.  Tap the **"Paid"** button. This will save the bill to history and reset the screen.
6.  Navigate to the **History Screen** to view past transactions. Tap the arrow icon on any card to see item details.

---
**Author:** Bhumi2507
