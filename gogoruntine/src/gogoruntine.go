package main

import (
	"fmt"
)

func sum(a []int, c chan int) {
    sum := 0
    for _, v := range a {
        sum += v
    }
    c <- sum  // send sum to c
}

func main() {

    a := []int{7, 2, 8, -9, 4, 0}
    c := make(chan int)
    go sum(a[:len(a)/2], c)
    go sum(a[len(a)/2:], c)	
    x, y := <- c, <- c    // receive from c
    fmt.Println(x, y, x + y)

	go testRange(10, c)
	
	for i:= range c {
		fmt.Println(i)
	}

}

func testRange(n int, c chan int){
	x, y := 1, 1
	
	for i := 0; i < n; i++ {
		c <- x
		x ,y = y, x + y
	}
	
	close(c)
	
	
}