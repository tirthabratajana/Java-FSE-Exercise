// Task 1: JavaScript Basics & Setup
console.log("Welcome to the Community Portal"); // Log to console [cite: 3]

// Use an alert to notify when the page is fully loaded [cite: 3]
window.onload = function() {
    alert("Welcome to the Community Portal! Page fully loaded.");
};

// Task 2: Syntax, Data Types, and Operators
const eventName = "Annual Charity Run"; // Use const for event name [cite: 4]
const eventDate = "2025-06-15"; // Use const for event date [cite: 4]
let availableSeats = 100; // Use let for seats [cite: 4]

// Concatenate event info using template literals [cite: 4]
console.log(`Event: ${eventName}, Date: ${eventDate}, Seats Available: ${availableSeats}`);

// Task 5: Define Event constructor or class [cite: 9]
// Task 10: Use let, const, default parameters in functions (Default parameter for category) [cite: 14]
class Event {
    constructor(id, name, date, category = 'community', seats, isUpcoming = true) { // Default parameter for category
        this.id = id;
        this.name = name;
        this.date = new Date(date);
        this.category = category;
        this.seats = seats;
        this.isUpcoming = isUpcoming;
    }

    // Task 5: Add checkAvailability() to prototype [cite: 9]
    checkAvailability() {
        return this.seats > 0;
    }

    // Task 2: Use ++ or -- to manage seat count on registration [cite: 4]
    // (This will be called when a user registers)
    registerSeats(numSeats) {
        if (this.seats >= numSeats) {
            this.seats -= numSeats; // Decrement seats
            return true;
        }
        return false;
    }
}

// Mock Event Data for Tasks 3, 6, 9
let communityEvents = [
    new Event(1, "Summer Music Fest", "2025-07-20", "music", 50, true),
    new Event(2, "Local Food Workshop", "2025-08-05", "workshop", 20, true),
    new Event(3, "Community Sports Day", "2025-06-01", "sport", 120, false), // Past event for testing
    new Event(4, "Art Exhibition", "2025-09-10", "community", 30, true),
    new Event(5, "Autumn Concert", "2025-10-25", "music", 75, true),
    new Event(6, "Photography Workshop", "2025-11-12", "workshop", 15, true),
    new Event(7, "Chess Tournament", "2025-07-01", "sport", 0, true) // Full event for testing
];

// Task 10: Use spread operator to clone event list before filtering [cite: 15]
let currentFilteredEvents = [...communityEvents];

// Task 7: Access DOM elements using querySelector() [cite: 11]
const eventsContainer = document.querySelector('#eventsContainer');
const registrationForm = document.querySelector('#registrationForm');
const regEventNameInput = document.querySelector('#regEventName');
const regEmailInput = document.querySelector('#regEmail');
const regSeatsInput = document.querySelector('#regSeats');
const registerBtn = document.querySelector('#registerBtn');
const loadingSpinner = document.querySelector('#loadingSpinner');
const messageArea = document.querySelector('#messageArea');
const eventCategoryFilter = document.querySelector('#eventCategory');
const searchEventInput = document.querySelector('#searchEvent');

const eventNameError = document.querySelector('#eventNameError');
const emailError = document.querySelector('#emailError');
const seatsError = document.querySelector('#seatsError');

// Task 4: Create addEvent() [cite: 7]
function addEvent(id, name, date, category, seats) {
    const newEvent = new Event(id, name, date, category, seats);
    // Task 6: Add new events using .push() [cite: 10]
    communityEvents.push(newEvent);
    renderEvents();
}

// Task 4: Create registerUser() [cite: 7]
// Task 10: Destructuring to extract event details [cite: 14]
function registerUser(eventId, { name, email, numSeats }) { // Destructuring
    // Task 13: Log form submission steps and check fetch request payload [cite: 19]
    console.log("Attempting to register user:", name, email, "for event ID:", eventId, "seats:", numSeats);

    const event = communityEvents.find(e => e.id === eventId);
    if (!event) {
        throw new Error("Event not found.");
    }

    // Task 3: Wrap registration logic in try-catch to handle errors [cite: 5]
    try {
        if (!event.checkAvailability() || event.seats < numSeats) {
            throw new Error(`Not enough seats available for ${event.name}. Only ${event.seats} left.`);
        }

        if (event.registerSeats(numSeats)) {
            console.log(`User ${name} registered for ${numSeats} seats in ${event.name}.`);
            return true;
        } else {
            throw new Error("Registration failed unexpectedly.");
        }
    } catch (error) {
        console.error("Registration error:", error.message);
        throw error; // Re-throw to be caught by the form submission handler
    }
}

// Task 4: Use closure to track total registrations for a category [cite: 7]
function createCategoryRegistrationTracker() {
    const registrations = {}; // Private variable

    return function(category, count) {
        registrations[category] = (registrations[category] || 0) + count;
        console.log(`Total registrations for ${category}: ${registrations[category]}`);
    };
}
const trackCategoryRegistrations = createCategoryRegistrationTracker();


// Function to render events dynamically [cite: 11]
function renderEvents(eventsToDisplay = currentFilteredEvents) {
    eventsContainer.innerHTML = ''; // Clear previous events

    // Task 3: Loop through the event list and display using forEach() [cite: 6]
    eventsToDisplay.forEach(event => {
        // Task 3: Use if-else to hide past or full events [cite: 5]
        const isPastOrFull = !event.isUpcoming || !event.checkAvailability();
        const cardDiv = document.createElement('div');
        cardDiv.classList.add('event-card');
        if (isPastOrFull) {
            cardDiv.classList.add('hidden'); // Hide the card if past or full
        }
        // Task 7: Create and append event cards using createElement() [cite: 11]
        cardDiv.innerHTML = `
            <h3>${event.name}</h3>
            <p>Date: ${event.date.toLocaleDateString()}</p>
            <p>Category: ${event.category}</p>
            <p>Seats Left: ${event.seats}</p>
            <button data-event-id="${event.id}" ${isPastOrFull ? 'disabled' : ''}>${isPastOrFull ? 'N/A' : 'Register'}</button>
        `;
        eventsContainer.appendChild(cardDiv);
    });

    // Task 8: Use onclick for "Register" buttons [cite: 12]
    document.querySelectorAll('.event-card button').forEach(button => {
        button.onclick = (e) => {
            const eventId = parseInt(e.target.dataset.eventId);
            // Pre-fill registration form with event name
            const selectedEvent = communityEvents.find(e => e.id === eventId);
            if (selectedEvent) {
                regEventNameInput.value = selectedEvent.name;
            }
            alert(`You clicked to register for Event ID: ${eventId}. Please fill out the form below.`);
        };
    });
}

// Task 4: Create filterEventsByCategory() [cite: 7]
// Task 4: Pass callbacks to filter functions for dynamic search [cite: 7]
function filterEventsByCategory(category, callback) {
    let filtered;
    // Task 6: Use .filter() to show only music events (and other categories) [cite: 10]
    if (category === 'all') {
        filtered = [...communityEvents]; // Task 10: Use spread operator to clone event list [cite: 15]
    } else {
        filtered = communityEvents.filter(event => event.category === category);
    }
    currentFilteredEvents = filtered; // Update current filtered list
    if (callback) {
        callback(filtered);
    }
}

// Task 6: Use .map() to format display cards (e.g., "Workshop on Baking") [cite: 10]
function formatEventForDisplay(event) {
    // This function is for demonstration of .map, not directly used in renderEvents due to DOM creation
    return `${event.name} on ${event.date.toLocaleDateString()} (Category: ${event.category})`;
}
console.log("Formatted events for display:", communityEvents.map(formatEventForDisplay));


// Task 8: Use onchange to filter events by category [cite: 12]
eventCategoryFilter.onchange = (e) => {
    filterEventsByCategory(e.target.value, (filtered) => {
        renderEvents(filtered);
    });
};

// Task 8: Use keydown to allow quick search by name [cite: 12]
searchEventInput.addEventListener('keyup', (e) => { // Using keyup for better user experience
    const searchTerm = e.target.value.toLowerCase();
    const filteredBySearch = currentFilteredEvents.filter(event =>
        event.name.toLowerCase().includes(searchTerm)
    );
    renderEvents(filteredBySearch);
});

// Task 11: Working with Forms
// Task 11: Capture name, email, and selected event using form.elements [cite: 16]
// Task 11: Prevent default form behavior using event.preventDefault() [cite: 16]
registrationForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    // Clear previous errors
    eventNameError.textContent = '';
    emailError.textContent = '';
    seatsError.textContent = '';
    regEventNameInput.classList.remove('invalid');
    regEmailInput.classList.remove('invalid');
    regSeatsInput.classList.remove('invalid');

    const formElements = e.target.elements;
    const eventName = formElements.eventName.value;
    const email = formElements.email.value;
    const seats = parseInt(formElements.seats.value);

    // Task 11: Validate inputs and show errors inline [cite: 16]
    let isValid = true;
    if (!eventName) {
        eventNameError.textContent = 'Event name is required.';
        regEventNameInput.classList.add('invalid');
        isValid = false;
    }
    if (!email || !email.includes('@')) {
        emailError.textContent = 'Valid email is required.';
        regEmailInput.classList.add('invalid');
        isValid = false;
    }
    if (isNaN(seats) || seats <= 0) {
        seatsError.textContent = 'Number of seats must be a positive number.';
        regSeatsInput.classList.add('invalid');
        isValid = false;
    }

    if (!isValid) {
        messageArea.textContent = 'Please correct the errors in the form.';
        messageArea.className = 'error';
        return;
    }

    const selectedEvent = communityEvents.find(event => event.name === eventName);
    if (!selectedEvent) {
        eventNameError.textContent = 'Selected event not found.';
        regEventNameInput.classList.add('invalid');
        messageArea.textContent = 'Selected event not found.';
        messageArea.className = 'error';
        return;
    }

    loadingSpinner.style.display = 'block'; // Show loading spinner [cite: 13]
    messageArea.textContent = '';
    messageArea.className = '';

    // Task 12: Use fetch() to POST user data to a mock API [cite: 17]
    // Task 9: Fetch events from a mock JSON endpoint (simulated) [cite: 13]
    // Task 9: Rewrite using async/await and show loading spinner [cite: 13]
    try {
        // Simulate an API call
        const mockApiResponse = await new Promise(resolve => {
            // Task 12: Use setTimeout() to simulate a delayed response [cite: 17]
            setTimeout(() => {
                const success = registerUser(selectedEvent.id, { name: eventName, email, numSeats: seats });
                if (success) {
                    // Task 4: Use closure to track total registrations for a category [cite: 7]
                    trackCategoryRegistrations(selectedEvent.category, seats);
                    resolve({ success: true, message: "Registration successful!" });
                } else {
                    resolve({ success: false, message: "Registration failed due to availability or an internal error." });
                }
            }, 1500); // Simulate network delay
        });

        // Task 12: Show success/failure message after submission [cite: 17]
        if (mockApiResponse.success) {
            messageArea.textContent = mockApiResponse.message;
            messageArea.className = 'success';
            // Task 7: Update UI when user registers or cancels [cite: 11]
            renderEvents(); // Re-render to show updated seat count
            // Clear form
            registrationForm.reset();
        } else {
            messageArea.textContent = mockApiResponse.message;
            messageArea.className = 'error';
        }
    } catch (error) {
        console.error("Fetch or registration error:", error);
        messageArea.textContent = `An error occurred: ${error.message}`;
        messageArea.className = 'error';
    } finally {
        loadingSpinner.style.display = 'none'; // Hide loading spinner
    }
});


// Initial render of events
renderEvents();

// Task 5: List object keys and values using Object.entries() [cite: 9]
console.log("Event object entries for the first event:");
if (communityEvents.length > 0) {
    for (const [key, value] of Object.entries(communityEvents[0])) {
        console.log(`${key}: ${value}`);
    }
}

// Task 14: jQuery and JS Frameworks
// Scenario: Use jQuery to simplify DOM tasks. [cite: 20]
// Objective: Understand and use jQuery. [cite: 20]

// Use $('#registerBtn').click(...) to handle click events [cite: 20]
$(document).ready(function() {
    $('#registerBtn').click(function() {
        // The main form submission handler above handles the logic, this just shows jQuery usage
        console.log("jQuery click handler for #registerBtn triggered.");
    });

    // Use .fadeIn() and .fadeOut() for event cards [cite: 20]
    // Example: Add a button to hide/show all event cards using jQuery
    $('main').prepend('<button id="toggleCardsBtn" style="margin-bottom: 20px; padding: 10px; background-color: #ffc107; border: none; border-radius: 5px; cursor: pointer;">Toggle Event Cards</button>');

    $('#toggleCardsBtn').click(function() {
        $('.event-card').each(function() {
            if ($(this).is(':visible')) {
                $(this).fadeOut(500); // Fade out if visible
            } else {
                $(this).fadeIn(500); // Fade in if hidden
            }
        });
    });
});

// Mention one benefit of moving to frameworks like React or Vue [cite: 20]
console.log("Benefit of frameworks like React or Vue:");
console.log("They offer component-based architecture, which makes building complex UIs more modular, reusable, and maintainable, especially for large-scale applications, by efficiently managing the DOM and state changes.");