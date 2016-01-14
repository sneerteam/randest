# Randest - Tower Defense Driven Development (TDDD)

A framework that makes testing software systems easier and more fun, like playing tower defense (TD) games. It is especially suited for complex, asynchronous and even distributed systems.

![Flash Tower Defense 2](http://i.imgur.com/YZBRjSt.png)

Think of the events pouring into your system as the invading critters and writing code to handle them as carefully placing towers to handle the invasion.

Randest will randomly exercise your code and stress it at the same time.

Randest is NOT a graphic or gaming framework. The image above is from "Flash Tower Defense 2" and is just conceptual.


# Why?

- Even when I am bored and tired of coding, I can still spend several hours playing TD. Beyond graphics and sound, I feel the **reactive** mindset needed to play is much more relaxing, more engaging and less demanding than the **proactive** mindset needed to come up with traditional test cases.

- Traditional testing techniques are just not good enough to test complex interaction of asynchronous code. Randest was created to test [Sneer](http://sneer.me).


# How?

Instead of having to come up with clever test cases, you just have to list the events that could happen and their average period in millis.

To test interactions between users in a chat system, for example:

```
{ "new-user"          (* 1  day)
  "quit-user"         (* 3  day)
  "user-send-message" (* 5  minute)
  "user-send-pic"     (* 30 minute) }
```

## Basic Level

Start Randest passing it two functions that you define:
1. **event-periods** - Given the state of your system, returns a map with the possible events as keys and their average periods as values. See example above.
2. **apply-event** - Given the event name and Returns the new system state.

To test interactions between users in a chat system, for example, that function would return:

```
{ "new-user"           (* 1 day)
  "quitting-user"
  "user1-send-message" (* 5  minute)
  "user1-send-pic"     (* 30 minute)
  "user2-send-message" (* 5  minute)
  "user2-send-pic"     (* 30 minute) }
```


Randest will loop through the following steps until an exception is thrown:

- It will call your event-periods function that you must create.
- 
3. Call the function 

## Intermediate Level

  "network-packet-sent"      10
  "network-packet-lost"      100
  "network-packet-corrupted" 1000 

## Advanced Level
