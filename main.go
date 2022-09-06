package main

import (
	"encoding/xml"
	"fmt"
	"io/ioutil"
	"os"
	"text/template"
	"time"
)

func main() {
	inDateFormat := "2006-01-02T15:04:05-07:00"
	inDateFormatWithoutZ := "2006-01-02T15:04:05"
	outTimeFormat := "15:04"
	outDateFormat := "02.01.2006"
	// Open our xmlFile
	xmlFile, err := os.Open("./in/mini.xml")
	// if we os.Open returns an error then handle it
	if err != nil {
		fmt.Println(err)
	}

	fmt.Println("Successfully Opened file")
	// defer the closing of our xmlFile so that we can parse it later on
	defer xmlFile.Close()
	byteValue, _ := ioutil.ReadAll(xmlFile)

	var roster Roster
	err = xml.Unmarshal(byteValue, &roster)
	if err != nil {
		fmt.Println(err)

	}

	lessons := roster.Entries.Lessons
	fmt.Println(len(lessons))

	var fl []Lesson
	for _, l := range lessons {
		ti, err := time.Parse(inDateFormat, l.Date)
		if err != nil {
			ti, err = time.Parse(inDateFormatWithoutZ, l.Date)
			if err != nil {
				fmt.Println(err)
			}
		}
		fl = append(fl, Lesson{
			Subject:      l.Subject,
			MeetingPlace: l.MeetingPlace,
			Weekday:      localizeWeekday(ti),
			Date:         ti.Format(outDateFormat),
			Time:         ti.Format(outTimeFormat),
		})
	}

	t, err := template.ParseFiles("./template/rosterTemplate.html")
	if err != nil {
		panic(err)
	}
	of, err := os.Create("./out/out.txt")
	if err != nil {
		panic(err)
	}
	err = t.Execute(of, fl)
	if err != nil {
		panic(err)
	}

}

var localizedWeekdays = []string{
	"Sonntag",
	"Montag",
	"Dienstag",
	"Mittwoch",
	"Donnerstag",
	"Freitag",
	"Samstag",
}

func localizeWeekday(t time.Time) string {
	return localizedWeekdays[int(t.Weekday())]

}

type Roster struct {
	Entries Entries `xml:"Entries"`
}
type Entries struct {
	Lessons []LessonRaw `xml:"RosterEntry"`
}

type LessonRaw struct {
	Date              string `xml:"Date"`
	MeetingPlace      string `xml:"MeetingPlace"`
	ResponsiblePerson string `xml:"ResponsiblePerson"`
	Subject           string `xml:"Subject"`
}

type Lesson struct {
	Weekday      string
	Date         string
	Time         string
	Subject      string
	MeetingPlace string
}
