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
  - Step 4: update group balance sheet.
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



-----
READ ME by GPT

Splitwise – Low Level Design (LLD)

1. Problem Overview

Splitwise is an expense-sharing system that allows users to split expenses within groups and settle debts with minimal transactions.

The system supports:
•	Multiple users and groups
•	Different expense split types
•	Group-level and global balance tracking
•	Debt simplification to reduce unnecessary transactions

⸻

2. Functional Requirements

Core Features
•	Users can create groups and add members
•	Users can add expenses inside a group
•	Expenses can be split using:
•	Equal split
•	Percentage-based split
•	Exact amount split
•	Users can view:
•	Balance with each member in a group
•	Net balance across all groups (global view)
•	System provides Simplify Debt feature to minimize transactions

Simplify Debt
•	Removes circular debts
•	Reduces number of payments
•	Final net balance remains unchanged

⸻

3. High-Level Design Overview
   •	GroupManager – Entry point for all group-related operations
   •	ExpenseManager – Handles expense creation and validation
   •	SplitStrategy – Decides how an expense is split
   •	BalanceSheet – Stores who owes whom and how much
   •	SimplifyDebtService – Optimizes balances at group level

GroupManager acts as the aggregate root and coordinates all operations.

⸻

4. Core Entities (Nouns)
   •	User
   •	Group
   •	Expense
   •	Split
   •	BalanceSheet

Supporting Components
•	UserManager
•	GroupManager
•	ExpenseManager
•	SplitStrategy (interface / abstract class)

⸻

5. Class Responsibilities

User
•	Represents a system user
•	Has userId and basic metadata

Group
•	Contains groupId and list of users
•	Maintains reference to group BalanceSheet

Expense
•	Contains:
•	expenseId
•	paidBy user
•	total amount
•	groupId
•	List

Split
•	Holds:
•	userId
•	amount owed

BalanceSheet
•	Stores balances as:
•	who owes whom and how much
•	Maintains group-level balances only
•	Does NOT perform business logic

⸻

6. Split Strategy Design

SplitStrategy (Interface / Abstract Class)

Input:
- Map<UserId, Double> userInputMap
- totalAmount

Output:
- List<Split>

Implementations
•	EqualSplitStrategy
•	PercentageSplitStrategy
•	ExactSplitStrategy

This follows the Strategy Pattern and adheres to the Open–Closed Principle.

⸻

7. User Flow

User → Add Expense
→ Select Split Type
→ Split Strategy calculates splits
→ ExpenseManager validates
→ BalanceSheet updated

User → View Group Balance
User → View Global Balance


⸻

8. Simplify Debt Design

Responsibility
•	Simplify Debt is triggered from GroupManager
•	Logic does NOT reside inside BalanceSheet

Algorithm (Greedy)
1.	Calculate net balance for each user
2.	Separate users into:
•	Debtors (negative balance)
•	Creditors (positive balance)
3.	Sort both lists
4.	Greedily match debtor → creditor
5.	Generate minimum transactions
6.	Update group BalanceSheet

Example

Before Simplify:
A owes B 100
B owes C 100
C owes A 100

After Simplify:
No transactions needed (circular debt removed)

Complexity
•	Time Complexity: O(N log N)
•	Space Complexity: O(N)

Greedy approach is sufficient; over-optimization adds complexity without real benefit.

⸻

9. User-Level Balance Handling

Two approaches considered:

Option 1: Pre-compute and store
•	Clear and rebuild user balances after every simplify
•	Higher complexity

Option 2: Compute on query (Chosen)
•	Iterate through groups user belongs to
•	Aggregate balances dynamically

Reason:
User-level queries are infrequent, so on-demand computation keeps the system simple and clean.

⸻

10. Design Patterns Used
    •	Strategy Pattern – For split types
    •	Singleton Pattern – (Optional) for managers
    •	Facade-like Design – GroupManager as entry point
    •	Separation of Concerns – Data vs business logic

⸻

11. Design Decisions Summary
    •	BalanceSheet stores data only
    •	GroupManager controls workflows
    •	Simplify Debt is a group-level concern
    •	Greedy algorithm preferred over complex graph algorithms

⸻

12. Future Enhancements
    •	Multi-currency support
    •	Expense editing and deletion
    •	Persistent storage (DB)
    •	Concurrency handling
    •	Payment gateway integration

⸻

13. Notes for Interview Discussion
    •	Focus on clarity over over-engineering
    •	Explain trade-offs clearly
    •	Keep debt simplification simple and correct

⸻

Author: Baburao