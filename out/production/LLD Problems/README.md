# LLD-Problems
Solving Low Level Design Problems

ConcurrentHashMap<>() for thread safe HashMap
CopyOnWriteArraySet<>() for thread Safe set
CopyOnWriteArrayList<>() for thread safe list


Date
LocalDate.now() gives date of type of LocalDate -> Format(YYYY - MM - DD)
Methods to compare
1. date1.isBefore(date2)
2. date1.isAfter(date2)
3. date1.isEqual(date2)
4. date1.compareTo(date2)

plusDays() -> gives Updated date of LocalDate type
minusDays()

Time
LocalTime.now() gives exact time in format (HH: MM: SS: NanoSec) of type LocalTime
-> Same methods as date
plusHours()
plusMinutes()
minusMinutes()
.getHour() -> gets the hour


AtomicLong reservationId = new AtomicLong(0); // Long value which is thread safe with inital value 0
reservationId.incrementAndGet(); // Increment value by 1


