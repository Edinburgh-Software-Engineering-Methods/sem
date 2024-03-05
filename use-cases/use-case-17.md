# Use Case: Produce Report of Capital Cities in the World

## Characteristic Information

### Goal in Context

As a *user*, I want *to produce a report of all the capital cities in the world organized by largest population to smallest* so that *demographics of capital cities can be examined globally*.

### Scope

Global capital city population data.

### Level

Primary task.

### Preconditions

The database has up-to-date population data for all the capital cities in the world. 

### Success End Condition

A report is available for all the capital cities in the world organized by largest population to smallest.

### Failed End Condition

No report is generated.

### Primary Actor

User.

### Trigger

The user requests the system for the population data of all the capital cities in the world.

## Main Success Scenario

1. User requests to produce a report of all the capital cities in the world organized by largest population to smallest.
2. System extracts the population data of all the capital cities in the world.
3. System organizes all the capital cities in the world by largest population to smallest.
4. System produces a report of all the capital cities in the world organized by largest population to smallest.
5. User receives the report.

## Extensions

3. **Data does not exist**
   1. The system asks the user to choose correctly.

## Sub-Variations

None.

## Schedule

**Due Date**: Release 5.0