1. Clarify Requirements
    - User should be able to split expenses among a group of people.
    - User can split equally, percentage-wise, or by specific amounts.
    - STAR - "Simplify Debt" feature to minimize the number of transactions.
       - Circular debt simplification.
    - Users can view their balance with each member of the group.
    - BalanceSheet - User should be able to see how much they owe or
        are owed by each member in group level and global level too


2. Core Entities - Nouns
    - User
    - Expense
    - Group
    - BalanceSheet
    - Split Type (Equal, Percentage, Exact)
    - ExpenseManager
    - UserManager
    - GroupManager
    - SplitStrategy - Abstract class/interface for different split types.
    - Split - Object holds UserId and amount.

Split Strategy - Abstract class - while creating object pass - UserInputMap (string, double), totalAmount - After that call
                 splitInfo() method to get List<Split>.

Expense - Must contain List<Split> to hold how much each user owes.
3. User Flow

User -> Adds Expense -> Select Split Type -> Click "Split" (Update Balance Sheet)
User -> Views Balance with Group Members, Also net

4. Design Interfaces and Abstract Classes


5. Implement Main Features
6. Explain about Patterns Used