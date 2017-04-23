# Interaction Designer Requirements
## User Experience
- be able to create and save new interactions as a CSV
- parse CSV's to open interactions
- create new "actions"


## Actions
- have a list of properties the user can modify
	- e.g. Action Message has a "message" property
- each action has a special identifier based on class name

## Action Branchers
- Branch between actions

## Interaction Table
- connects the actions in the interaction
- maps from one action to at least one action via id

# Interaction Designer Specifications
## Actions
- id: integer
- properties: HashMap<String,Object>

## Action Branchers

## Interaction Table
- table: {{int}}
	- index of table is source action id	
