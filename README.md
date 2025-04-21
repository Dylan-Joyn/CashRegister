# Design Patterns Implementation for Making Change Calculator

## Patterns Implemented
This project implements two design patterns: Observer and Command.

## Observer Pattern
- Purpose: Automatically update the UI when the purse contents change
- How it works: The Purse class notifies registered observers (like PursePanel) whenever its state changes
- Advantage: Eliminates manual UI refresh calls and improves code organization

## Command Pattern
- Purpose: Encapsulate user actions as objects and enable undo functionality
- How it works: Each make change operation becomes a command object that can be executed and undone
- Advantage: Adds undo capability and makes the code more modular

## Benefits Achieved
1. Automatic UI updates without manual refresh calls
2. Undo functionality for reverting changes
3. Better separation between user interface and business logic
4. More maintainable and extensible codebase
5. Ability to easily add more features in the future
