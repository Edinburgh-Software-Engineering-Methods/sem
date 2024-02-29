# Use Case: Produce A report of Countries by Population in a continent

## CHARACTERISTIC INFORMATION

## Goal in Context
As a user, I want to produce reports of all the countries in a continent organized by largest population to smallest so that population analysis  can be performed. 

## Scope
Continental population analysis.

## Level
Primary task.

## Preconditions
The database has up-to-date population data for all countries in a continent

## Success End Condition
Reports are generated for all the countries in a continent organized by population from largest to smallest.

## Failed End Condition
No reports are generated.

## Primary Actor
User

## Trigger
The user requests to produce population reports of all the countries in a continent 

## Main Success Scenario
1. The user request the system to give the population data of all the countries in a continent organized by largest population to smallest.
2. The system retrieves the latest population data for all the countries in a requested continent. 
3. The system organizes the countries in requested continent by population and sort them from largest to smallest.
4. The system produce a population report of all the countries in requested continent. 
5. The system presents the report to the user for review.

## Extensions

**Data does not exist**
1. The system asks the user to choose correctly.

## Sub-variations
None.

## Schedule
**Due Date:** Not Specified
