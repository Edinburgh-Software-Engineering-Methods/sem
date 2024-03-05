# Use Case: Produce Report of Top N Populated Cities in the World

## Characteristic Information

### Goal in Context

As a *user*, I want *to produce a report of the top N populated cities in the world* *where N is provided by me* so that *I can understand the most populated cities by specific choice*.

### Scope

Global city population data.

### Level

Primary task.

### Preconditions

The database has up-to-date population data for the top N populated cities in the world

### Success End Condition

A report is available for the top N populated cities in the world, where N is specified by user

### Failed End Condition

No report is generated.

### Primary Actor

User.

### Trigger

The user requests the system for the population data of top N populated cities in the world. 

## Main Success Scenario

1. User specifies the value of N for the report.
2. System extracts global population data for all top N populated cities. 
3. System identifies the top N populated cities based on the specified value.
4. System produce a report for the top N populated cities in the world.
5. User receives the report from the system.


## Extensions

3. **Invalid Value of N**:
   1. System notifies the user that the provided value of N is invalid.

## Sub-Variations

None.

## Schedule

**Due Date**: Release 4.0
