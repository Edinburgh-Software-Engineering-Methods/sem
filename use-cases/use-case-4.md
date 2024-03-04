# Use Case: Produce a Report of the Top N Populated Countries in the world

## Characteristic Information

### Goal in Context

As a *user*, I want *to produce a report of the top N populated countries in the world*, where *N is provided by me* so that *I can understand the different population analysis of specific choice.*

### Scope

Global population data.

### Level

Primary task.

### Preconditions

The user has access to global population data. 
The database has up-to-date population data for the top N countries in the world

### Success End Condition

A report is generated for the top N populated countries in the world where N is provided by the user

### Failed End Condition

No report is produced. 

### Primary Actor

User.

### Trigger

The user requests to produce a population report of top N populated countries in the world. 

## Main Success Scenario

1. User specifies the value of N for the report.
2. System retrieves global population data for all top N populated countries. 
3. System identifies the top N populated countries based on the specified value.
4. System produce a report for the top N populated countries in the world. 
5. User receives the report from the system. 

## Extensions

3. **Invalid Value of N**:
    1. System notifies the user that the provided value of N is invalid.

## Sub-Variations

None.

## Schedule

**Due Date**: Release 2.0
