#!/bin/sh

sed -i.bak 's/<MeetingPlace\/>/<MeetingPlace> <\/MeetingPlace>/g' $1
sed -i.bak 's/<ResponsiblePerson\/>/<ResponsiblePerson> <\/ResponsiblePerson>/g' $1
sed -i.bak 's/<Subject\/>/<Subject> <\/Subject>/g' $1
