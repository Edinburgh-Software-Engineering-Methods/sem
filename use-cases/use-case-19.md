# Use Case: Produce Report of Capital Cities in a region

## Characteristic Information

### Goal in Context

As a *user*, I want *to produce a report of all the capital cities in a region organized by largest population to smallest* *so that I can analyze the regional capital cities.*

### Scope

Capital city population data in a region.

### Level

Primary task.

### Preconditions

The database has up-to-date population data for all the capital cities in a region.

### Success End Condition

A report is available for all the capital cities in a region organized by largest population to smallest.

### Failed End Condition

No report is generated.

### Primary Actor

User.

### Trigger

The user requests the system for the population data of all the capital cities in a region. 

## Main Success Scenario

1. User requests to produce a report of all the capital cities in a region organized by largest population to smallest.
2. System extracts the population data of all the capital cities in the world.
3. System organizes all the capital cities in a region by largest population to smallest.
4. System produces a report of all the capital cities in a region organized by largest population to smallest.
5. User receives the report.

## Extensions

3. **Data does not exist**
   1. The system asks the user to choose correctly.

## Sub-Variations

None.

## Schedule

**Due Date**: Release 5.0