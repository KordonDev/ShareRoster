#!/bin/sh

sed -i 's/<MeetingPlace\/>/<MeetingPlace> <\/MeetingPlace>' $1
sed -i 's/<ResponsiblePerson\/>/<ResponsiblePerson> <\/MeetingPlace>' $1
sed -i 's/<Subject\/>/<Subject> <\/MeetingPlace>' $1
