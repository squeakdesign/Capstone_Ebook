document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    const fullName = form.querySelector('input[name="name"]');
    const mobileNumber = form.querySelector('input[name="mobileNumber"]');
    const email = form.querySelector('input[name="email"]');
    const address = form.querySelector('input[name="address"]');
    const city = form.querySelector('input[name="city"]');
    const state = form.querySelector('input[name="state"]');
    const pincode = form.querySelector('input[name="pincode"]');
    const password = form.querySelector('input[name="password"]');
    const confirmPassword = form.querySelector('input[name="cpassword"]');
    const profileImage = form.querySelector('input[name="img"]');

    form.addEventListener('submit', function(event) {
        let valid = true;
        let messages = [];

        // Full Name validation
        if (fullName.value.trim() === '') {
            valid = false;
            messages.push('Full Name is required.');
        }

        // Mobile Number validation
        if (mobileNumber.value.trim() === '') {
            valid = false;
            messages.push('Mobile Number is required.');
        } else if (!/^\d{10}$/.test(mobileNumber.value)) {
            valid = false;
            messages.push('Mobile Number must be 10 digits.');
        }

        // Email validation
        if (email.value.trim() === '') {
            valid = false;
            messages.push('Email is required.');
        } else if (!/^\S+@\S+\.\S+$/.test(email.value)) {
            valid = false;
            messages.push('Email is not valid.');
        }

        // Address validation
        if (address.value.trim() === '') {
            valid = false;
            messages.push('Address is required.');
        }

        // City validation
        if (city.value.trim() === '') {
            valid = false;
            messages.push('City is required.');
        }

        // State validation
        if (state.value.trim() === '') {
            valid = false;
            messages.push('State is required.');
        }

        // Pincode validation
        if (pincode.value.trim() === '') {
            valid = false;
            messages.push('Pincode is required.');
        } else if (!/^\d{6}$/.test(pincode.value)) {
            valid = false;
            messages.push('Pincode must be 6 digits.');
        }

        // Password validation
        if (password.value.trim() === '') {
            valid = false;
            messages.push('Password is required.');
        } else if (password.value.length < 4) {
            valid = false;
            messages.push('Password must be at least 4 characters long.');
        }

        // Confirm Password validation
        if (confirmPassword.value.trim() === '') {
            valid = false;
            messages.push('Confirm Password is required.');
        } else if (password.value !== confirmPassword.value) {
            valid = false;
            messages.push('Passwords do not match.');
        }

        // Profile Image validation (optional)
        // Uncomment the following lines if profile image is required
        // if (profileImage.value.trim() === '') {
        //     valid = false;
        //     messages.push('Profile Image is required.');
        // }

        if (!valid) {
            event.preventDefault();
            alert(messages.join('\n'));
        }
    });
});