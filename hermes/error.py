class PasswordNotAvailable(Exception):

    def __init__(self):
        super().__init__("Password not available")


class ConfigFileNotPresent(Exception):

    def __init__(self):
        super().__init__("No config file found")


class AuthenticationError(Exception):

    def __init__(self, error):
        super().__init__("Authentication error: " + error)
