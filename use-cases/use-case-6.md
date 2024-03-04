# Use Case: Produce a Report of the Top N Populated Countries in a region

## Characteristic Information

### Goal in Context

As a *user*, I want *to produce a report of the top N populated countries in a region*, where *N is provided by me* so that *I can understand the most populated countries within a region.*

### Scope

Regional population data.

### Level

Primary task.

### Preconditions

The user has access to all population data.
The database has up-to-date population data for all countries in a region. 

### Success End Condition

A report is generated for the top N populated countries in requested region where N is provided by the user

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

The user requests to produce a population report of top N populated countries in a region

## Main Success Scenario

1. User specifies the value of N for the report.
2. System retrieves the global population data for all top N populated countries.
3. System identifies the top N populated countries in a requested region based on the specified value.
4. System produce a report for the top N populated countries in requested region. 
5. User receives the report from the system.

## Extensions

3. **Invalid Value of N**:
    1. System notifies the user that the provided value of N is invalid.

## Sub-Variations

None.

## Schedule

**Due Date**: Release 2.0
