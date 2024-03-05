# Use Case: Produce Report of Top N Populated Capital Cities in a continent

## Characteristic Information

### Goal in Context

As a *user*, I want *to produce a report of the top N populated capital cities in a continent where N is provided by me* so that *I can examine the most populated top N capital cities in a continent.*

### Scope

Capital city population data in a continent.

### Level

Primary task.

### Preconditions

The database has up-to-date population data for all top N populated capital cities in a continent.

### Success End Condition

A report is produced for the top N populated capital cities in a continent based on the user-provided value of N.

### Failed End Condition

No report is generated.

### Primary Actor

User.

### Trigger

The user requests the system for the population data of top N populated capital cities in a continent.

## Main Success Scenario

1. User specifies the value of N for the report.
2. System extracts population data for all top N populated capital cities.
3. System identifies the top N populated capital cities in a continent based on the specified value.
4. System produce a report for the top N populated capital cities in a continent.
5. User receives the report from the system.

## Extensions

3. **Invalid Value of N**:
    1. System notifies the user that the provided value of N is invalid.

## Sub-Variations

None.

## Schedule

Due Date: Release 6.0