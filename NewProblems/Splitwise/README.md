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

Splitwise – Low Level Design (Code Aligned)

This document explains the design, code structure, and decisions behind the Splitwise implementation in this repository. The goal is clarity, correctness, and interview-readiness rather than over-optimization.

⸻

1. Problem Statement

Build an expense-sharing system similar to Splitwise where:
•	Users can form groups
•	Expenses can be split in multiple ways
•	Users can see who owes whom
•	Circular and redundant debts can be minimized

⸻

2. Key Features
   •	Group-based expense tracking
   •	Supported split types:
   •	Equal split
   •	Percentage split
   •	Exact amount split
   •	Group-level balance view
   •	Global (user-level) balance view
   •	Simplify Debt feature to reduce number of transactions

⸻

3. High-Level Design

User → GroupManager → ExpenseManager → SplitStrategy
↓
BalanceSheet
↓
SimplifyDebt (via GroupManager)

Design Principles Followed
•	Separation of concerns
•	Single Responsibility Principle
•	Open–Closed Principle
•	Simplicity over premature optimization

⸻

4. Package Structure

Splitwise
├── Models
│    ├── User
│    ├── Group
│    ├── Expense
│    ├── Split
│
├── Managers
│    ├── UserManager
│    ├── GroupManager
│    ├── ExpenseManager
│
├── Strategies
│    ├── SplitStrategy
│    ├── EqualSplitStrategy
│    ├── PercentageSplitStrategy
│    ├── ExactSplitStrategy
│
├── BalanceSheet
└── Driver / Main


⸻

5. Core Entities and Responsibilities

User (Model)
•	Represents a system user
•	Identified by userId

Group (Model)
•	Contains list of users
•	Has a single BalanceSheet

Expense (Model)
•	Represents a single expense
•	Contains:
•	paidByUserId
•	totalAmount
•	groupId
•	List

Split (Model)
•	Represents how much a user owes in an expense
•	Contains:
•	userId
•	amount

⸻

6. Managers

GroupManager (Aggregate Root)
•	Main entry point for group-level operations
•	Responsibilities:
•	Add expense to group
•	Fetch group balances
•	Trigger simplify debt

All interactions with BalanceSheet happen via GroupManager.

⸻

ExpenseManager
•	Creates expenses
•	Validates split correctness
•	Delegates split calculation to SplitStrategy

⸻

UserManager
•	Manages user creation and lookup

⸻

7. BalanceSheet Design

Responsibility
•	Stores balances in the form:

who owes whom → amount

	•	Maintains only group-level balances
	•	Acts as a data store, not a business logic holder

Why no logic inside BalanceSheet?
•	Keeps the class simple
•	Prevents mixing storage with computation
•	Easier to test and extend

⸻

8. Split Strategy Design (Strategy Pattern)

SplitStrategy (Interface / Abstract Class)

Input:
- userInputMap (userId → value)
- totalAmount

Output:
- List<Split>

Implementations
•	EqualSplitStrategy
•	PercentageSplitStrategy
•	ExactSplitStrategy

Benefits
•	New split types can be added without modifying existing code
•	Follows Open–Closed Principle

⸻

9. Simplify Debt Feature

What is Simplify Debt?
•	Minimizes number of transactions
•	Removes circular and redundant debts
•	Final net balance of each user remains unchanged

⸻

Where is Simplify Debt handled?
•	Triggered from GroupManager
•	Uses balances from BalanceSheet
•	Logic is external to BalanceSheet

⸻

Algorithm Used (Greedy)
1.	Compute net balance for each user
2.	Separate users into:
•	Debtors (negative net balance)
•	Creditors (positive net balance)
3.	Sort both lists
4.	Greedily match debtor → creditor
5.	Generate minimum settlement transactions
6.	Update BalanceSheet

⸻

Example

Before Simplify:
A owes B 100
B owes C 100
C owes A 100

After Simplify:
No transactions required


⸻

Complexity
•	Time Complexity: O(N log N)
•	Space Complexity: O(N)

Greedy approach is sufficient for practical Splitwise use cases.

⸻

10. User-Level Balance Handling

Options Considered
1.	Store and maintain global balances eagerly
2.	Compute balances on demand (Chosen)

Chosen Approach
•	When user balance is requested:
•	Iterate through all groups user belongs to
•	Aggregate net balances

Reason
•	User-level queries are infrequent
•	Avoids duplicated and inconsistent state

⸻

11. Design Patterns Used
    •	Strategy Pattern – Expense splitting
    •	Singleton (Optional) – Managers
    •	Facade-like Pattern – GroupManager as entry point
    •	Separation of Concerns – Data vs logic

⸻

12. Trade-offs and Decisions
    •	Greedy algorithm preferred over complex graph algorithms
    •	No concurrency handling at current scope
    •	Focus on correctness and clarity over optimization

⸻

13. Future Enhancements
    •	Persistent storage (Database)
    •	Expense edit / delete support
    •	Multi-currency handling
    •	Concurrency control
    •	Payment gateway integration

⸻

14. Interview Notes
    •	GroupManager is the aggregate root
    •	BalanceSheet is a dumb data holder
    •	Simplify Debt preserves net balance
    •	Design favors simplicity and extensibility

⸻

Author: Baburao