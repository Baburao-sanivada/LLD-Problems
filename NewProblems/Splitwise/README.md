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


ToDo:
- Implement "Simplify Debt" feature using graph algorithms to minimize transactions.
- Update Read Me Clearly explaining the design and patterns used.


Simplify Debt:
- Splitwise calculates net balance for each user, separates debtors and creditors, 
    and greedily matches them to reduce the number of transactions while keeping final balances unchanged.
- Approach
  - Step 1: Calculate net balance for each user.
  - Step 2: Separate users into debtors and creditors.
  - Step 3: Greedily match debtors and creditors to minimize transactions.
- Normal Greedy would be enough no need to over optimise use simple sorting and greedy matching.
- Time Complexity: O(N log N) due to sorting.

Questions:
1. Can we directly talk with balance sheet or we need to go through user and group manager?
   - Go from groupManager
2. Simple debt should go from group manager / balance sheet directly?
   - go from groupManager
3. How do you handle user level balances
   1. maintain a hashmap - if simplify debt is there then clear map and rebuild again by going through all groups
   2. DO it based on query - Not asked many times - Very rarely queried - we can go with this
