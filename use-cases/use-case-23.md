# Use Case: Produce Report for Population Data of People in Each Continent

## Characteristic Information

### Goal in Context

As a *user*, I want *to produce a report for the population of people, people living in cities, and people not living in cities in each continent* so that *population distribution of every continent can be examined*.

### Scope

Population data of each continent.

### Level

Primary task.

### Preconditions

The database has up-to-date population data of people in each continent. 

### Success End Condition

A report is produced for the population distribution of people, people living in cities, and people not living in cities in each continent.

### Failed End Condition

No report is generated.

### Primary Actor

User.

### Trigger

The user requests the system for the population data of people in the world. 

## Main Success Scenario

1. User requests to produce a report of people, people living in cities, and people not living in cities in each continent.
2. System extracts the population data of people in the world. 
3. System identify the population data of people, people living in cities, and people not living in cities in each continent.
4. System generates a report of people, people living in cities, and people not living in cities in each continent.
5. User receives the report.

## Extensions

3. **Data does not exist**
1. The system asks the user to choose correctly.

## Sub-Variations

None.

## Schedule

Due Date: Release 7.0