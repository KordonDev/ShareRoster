const parser = require('xml2json');
const fs = require('fs');

const Weekday = ['Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag', 'Sontag'];
const pad = (number) => number > 9 ? number : '0' + number;
const isPresent = (test) => (typeof test) === 'string';

const xmlFile = fs.readFileSync(__dirname + '/montag12018.xml', 'utf8');
const json = JSON.parse(parser.toJson(xmlFile));
const entries = json.Roster.Entries.RosterEntry;

const html = entries.map(entry => {
    const dateTime = new Date(entry.Date);
    const date = `${pad(dateTime.getDate())}.${pad(dateTime.getMonth() + 1)}.${dateTime.getFullYear()}`;
    const time = `${pad(dateTime.getHours())}:${pad(dateTime.getMinutes())} Uhr`;
    console.log(`${entry.Subject} ${isPresent(entry.MeetingPlace)}`)
   return `
    <tr>
        <td>${Weekday[dateTime.getDay()]}</td>
        <td>${date}</td>
        <td>${time}</td>
        <td>${entry.Subject}</td>
        <td>${entry.MeetingPlace}</td>
    </tr>
   `
}).join('');

fs.writeFileSync(__dirname + '/test.json', html);
