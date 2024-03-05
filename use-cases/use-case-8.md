# Use Case: Produce a Report of Cities in a continent Organized by largest population to smallest

## Characteristic Information

### Goal in Context

As a *user*, I want *to produce a report of all the cities in a continent organized by largest population to smallest* so that *I can analyze the specific population results of all the cities in a specific continent.*

### Scope

Population data of all the cities in a continent

### Level

Primary task.

### Preconditions

The database has up-to-date population data for all the cities in a continent

### Success End Condition

A report is available for all the cities in a continent organized by largest population to smallest.

### Failed End Condition

No report is generated.

### Primary Actor

User.

### Trigger

The user requests the system for the population data for all the cities in a continent.

## Main Success Scenario

1. User requests to produce a report of cities in a continent organized by largest population to smallest. 
2. System extracts the population data of all the cities in the world.
3. System organizes the cities in requested continent by largest population to smallest.
4. System generates a report of all the cities in requested continent organized by largest population to smallest.
5. User receives the report.

## Extensions

3. **Data does not exist**
   1. The system asks the user to choose correctly.

## Sub-Variations

None.

## Schedule

**Due Date**: Release 3.0