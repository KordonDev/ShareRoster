const parser = require('xml2json');
const fs = require('fs');

const Weekday = ['Sontag', 'Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag'];
const pad = (number) => number > 9 ? number : '0' + number;
const isPresent = (test) => (typeof test) === 'string';

const fileName = process.argv[2];
const xmlFile = fs.readFileSync(__dirname + '/' + fileName, 'utf8');
const json = JSON.parse(parser.toJson(xmlFile));
const entries = json.Roster.Entries.RosterEntry;

const tableBody = entries.map(entry => {
    const dateTime = new Date(entry.Date);
    const date = `${pad(dateTime.getDate())}.${pad(dateTime.getMonth() + 1)}.${dateTime.getFullYear()}`;
    const time = `${pad(dateTime.getHours())}:${pad(dateTime.getMinutes())} Uhr`;
   return `
        <tr>
            <td>${Weekday[dateTime.getDay()]}</td>
            <td>${date}</td>
            <td>${time}</td>
            <td>${entry.Subject}</td>
            <td>${isPresent(entry.MeetingPlace) ? entry.MeetingPlace : ''}</td>
        </tr>
   `
}).join('');


const html = `
    <table style="font-size: 15px" border="0" cellspacing="11">
        <tr>
            <th>Tag</th>
            <th>Datum</th>
            <th>Zeit</th>
            <th>Thema</th>
            <th>Treffpkt*</th>
        </tr>
        ${tableBody}
    </table>
    </p>
    </p>
    <p>* Legende:</p>
    <p>GH = Gerätehaus Langensteinbach</p>
    <p>SBH = Schelmenbushhalle</p>
    <p>HdSch = Heimdecor Schmidt</p>
`;

fs.writeFileSync(__dirname + '/' + fileName + '.html', html);
